package br.com.faculdadeinovatech.inovatech2.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.faculdadeinovatech.inovatech2.repository.EmailRepository;
import br.com.faculdadeinovatech.inovatech2.security.UserDetailsImpl;
import br.com.faculdadeinovatech.inovatech2.service.UsuarioService;


@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/recuperar-senha")
    public String recuperarSenha() {
        return "recuperar-senha";
    }

    @PostMapping("/recuperar-senha")
    public String enviarLinkRecuperacao(@RequestParam("email") String email, Model model) {
        Optional<String> token = usuarioService.criarTokenResetSenha(email);

        if (token.isPresent()) {
            emailRepository.sendPasswordResetEmail(email, token.get());
            model.addAttribute("mensagem", "Enviamos um link de recuperacao para o seu e-mail.");
            return "recuperar-senha";
        }

        model.addAttribute("erro", "E-mail nao encontrado.");
        return "recuperar-senha";
    }

    @GetMapping("/trocar-senha")
    public String trocarSenha(@RequestParam("token") String token, Model model) {
        if (!usuarioService.tokenResetSenhaValido(token)) {
            model.addAttribute("erro", "Link invalido ou expirado.");
            return "recuperar-senha";
        }

        model.addAttribute("token", token);
        model.addAttribute("segundosRestantes", usuarioService.segundosRestantesTokenResetSenha(token));
        return "trocar-senha";
    }

    @PostMapping("/trocar-senha")
    public String salvarNovaSenha(@RequestParam("token") String token,
            @RequestParam("novaSenha") String novaSenha,
            Model model) {
        if (!usuarioService.alterarSenhaPorToken(token, novaSenha)) {
            model.addAttribute("erro", "Link invalido ou expirado.");
            return "recuperar-senha";
        }

        return "redirect:/login?senhaAlterada";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserDetailsImpl usuarioLogado, Model model) {
        if (usuarioLogado != null) {
            model.addAttribute("nomeUsuarioLogado", usuarioLogado.getNomeUsuario());
        }

        return "home";
    }
    
}

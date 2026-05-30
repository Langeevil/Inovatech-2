package br.com.faculdadeinovatech.inovatech2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class BreadcrumbControllerAdvice {

    @ModelAttribute("breadcrumbItems")
    public List<Map<String, String>> breadcrumbItems(HttpServletRequest request) {
        return buildBreadcrumbItems(request);
    }

    @ModelAttribute("breadcrumbSchemaItems")
    public List<Map<String, Object>> breadcrumbSchemaItems(HttpServletRequest request) {
        List<Map<String, String>> items = buildBreadcrumbItems(request);
        List<Map<String, Object>> schemaItems = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            Map<String, String> item = items.get(i);
            schemaItems.add(Map.of(
                    "@type", "ListItem",
                    "position", i + 1,
                    "name", item.get("label"),
                    "item", item.get("url")));
        }

        return schemaItems;
    }

    private List<Map<String, String>> buildBreadcrumbItems(HttpServletRequest request) {
        String path = request.getRequestURI();
        String contextPath = request.getContextPath();

        if (!contextPath.isBlank() && path.startsWith(contextPath)) {
            path = path.substring(contextPath.length());
        }

        if (path.isBlank()) {
            path = "/";
        }

        if (path.equals("/") || path.equals("/inovatech")) {
            return List.of(item("Inicio", path));
        }

        if (path.equals("/home")) {
            return List.of(item("Painel", path));
        }

        if (path.equals("/login")) {
            return List.of(link("Inicio", "/"), item("Login", path));
        }

        if (path.equals("/recuperar-senha")) {
            return List.of(link("Login", "/login"), item("Recuperar senha", path));
        }

        if (path.equals("/trocar-senha")) {
            return List.of(link("Login", "/login"), item("Trocar senha", path));
        }

        if (path.equals("/usuarios/criar")) {
            return List.of(link("Inicio", "/"), item("Cadastro de usuario", path));
        }

        if (path.equals("/relatorios/alunos")) {
            return List.of(link("Painel", "/home"), item("Relatorio de alunos", path));
        }

        List<Map<String, String>> items = moduleBreadcrumb(path);
        if (!items.isEmpty()) {
            return items;
        }

        return List.of(item("Pagina atual", path));
    }

    private List<Map<String, String>> moduleBreadcrumb(String path) {
        List<ModuleBreadcrumb> modules = List.of(
                new ModuleBreadcrumb("/alunos", "Alunos", "aluno"),
                new ModuleBreadcrumb("/cursos", "Cursos", "curso"),
                new ModuleBreadcrumb("/disciplinas", "Disciplinas", "disciplina"),
                new ModuleBreadcrumb("/professores", "Professores", "professor"),
                new ModuleBreadcrumb("/pedidos", "Pedidos", "pedido"),
                new ModuleBreadcrumb("/produtos", "Produtos", "produto"),
                new ModuleBreadcrumb("/itensdopedido", "Itens do pedido", "item do pedido"));

        for (ModuleBreadcrumb module : modules) {
            if (!path.startsWith(module.prefix())) {
                continue;
            }

            List<Map<String, String>> items = new ArrayList<>();
            items.add(link("Painel", "/home"));

            String listUrl = module.prefix() + "/listar";

            if (path.equals(listUrl)) {
                items.add(item(module.label(), path));
                return items;
            }

            items.add(link(module.label(), listUrl));

            if (path.equals(module.prefix() + "/criar")) {
                items.add(item("Cadastrar " + module.singular(), path));
                return items;
            }

            if (path.startsWith(module.prefix() + "/editar/")) {
                items.add(item("Editar " + module.singular(), path));
                return items;
            }

            return items;
        }

        return List.of();
    }

    private Map<String, String> link(String label, String url) {
        return Map.of("label", label, "url", url);
    }

    private Map<String, String> item(String label, String url) {
        return Map.of("label", label, "url", url);
    }

    private record ModuleBreadcrumb(String prefix, String label, String singular) {
    }
}

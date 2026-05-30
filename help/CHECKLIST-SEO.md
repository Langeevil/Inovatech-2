# ✅ Checklist de Configuração SEO Final

Use este checklist para completar a configuração do SEO do projeto Inovatech.

---

## 🔧 CONFIGURAÇÃO NECESSÁRIA

### 1. Substituir Domínio Placeholder
**Status:** ⚠️ CRÍTICO - Fazer antes de publicar

Substitua `https://seu-site.com` por seu domínio real em:

- [ ] `index.html` - 3 ocorrências
- [ ] `home.html` - 3 ocorrências  
- [ ] `login.html` - 3 ocorrências
- [ ] `SeoController.java` - 1 ocorrência (variável `SITE_URL`)
- [ ] `robots.txt` - 2 ocorrências (sitemaps)
- [ ] `SEO-GUIDE.md` - Exemplos de documentação
- [ ] `EXEMPLO-SEO-listaAlunos.html` - Arquivo exemplo

**Como fazer:**
```bash
# Substituição rápida (Windows PowerShell)
Get-ChildItem -Recurse -Include "*.html", "*.java", "*.txt", "*.md" | 
  ForEach-Object {
    (Get-Content $_) -replace "https://seu-site.com", "https://seudominio.com" | 
    Set-Content $_
  }
```

---

### 2. Criar Favicon
**Status:** 📋 RECOMENDADO

Gere um favicon em 16x16, 32x32 ou 64x64 pixels:

**Ferramentas Grátis:**
- [ ] favicon-generator.org
- [ ] realfavicongenerator.net
- [ ] convertio.co

**Salvar em:**
```
src/main/resources/static/favicon.ico
```

**Teste:**
Verificar que aparece na aba do navegador quando acessar o site.

---

### 3. Criar Imagem OG (Open Graph)
**Status:** 📋 RECOMENDADO

Gere imagem para compartilhamento em redes sociais:

**Especificações:**
- Dimensão: **1200 x 630 pixels**
- Formato: JPG ou PNG
- Tamanho: < 5MB
- Conteúdo: Logo + nome "Sistema Inovatech"

**Ferramentas:**
- [ ] Canva.com (modelo de social media)
- [ ] Adobe Express
- [ ] Figma

**Salvar em:**
```
src/main/resources/static/og-image.png
```

**Referência:**
Use em todos os arquivos HTML e fragmentos no lugar de `https://seu-site.com/og-image.png`

---

### 4. Configurar Compressão Gzip
**Status:** 📋 RECOMENDADO (Performance)

Adicione ao `src/main/resources/application.properties`:

```properties
# ===== COMPRESSÃO GZIP (Performance & SEO) =====
server.compression.enabled=true
server.compression.min-response-size=1024
server.compression.excluded-mime-types=image/png,image/jpeg,image/gif,image/webp,video/mp4
```

**Benefício:** Reduz tamanho de transferência em até 80%

---

### 5. Ativar Cache HTTP
**Status:** 📋 RECOMENDADO (Performance)

Crie classe `WebConfig.java` em `src/main/java/.../inovatech2/config/`:

```java
package br.com.faculdadeinovatech.inovatech2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/css/**", "/js/**", "/images/**", "/favicon.ico")
            .addResourceLocations("classpath:/static/css/", 
                                 "classpath:/static/js/",
                                 "classpath:/static/images/",
                                 "classpath:/static/")
            .setCachePeriod(31536000) // 1 ano em segundos
            .setCacheControl(new CacheControl().maxAge(365, TimeUnit.DAYS).cachePublic());
    }
}
```

**Importações necessárias:**
```java
import java.util.concurrent.TimeUnit;
import org.springframework.http.CacheControl;
```

---

### 6. Aplicar a Todas as Páginas
**Status:** ⚠️ IMPORTANTE

Aplique os padrões de SEO a todas as páginas:

Páginas que já têm SEO:
- [x] `index.html`
- [x] `home.html`
- [x] `login.html`

Páginas que precisam de SEO (use `EXEMPLO-SEO-listaAlunos.html` como referência):
- [ ] `recuperar-senha.html`
- [ ] `trocar-senha.html`
- [ ] `aluno/formularioAluno.html`
- [ ] `aluno/listaAlunos.html`
- [ ] `curso/formularioCurso.html`
- [ ] `curso/listaCursos.html`
- [ ] `disciplina/formularioDisciplina.html`
- [ ] `disciplina/listaDisciplinas.html`
- [ ] `professor/formularioProfessor.html`
- [ ] `professor/listaProfessores.html`
- [ ] `pedido/formularioPedido.html`
- [ ] `pedido/listaPedidos.html`
- [ ] `produto/formularioProduto.html`
- [ ] `produto/listaProdutos.html`
- [ ] `relatorio/relatorioAlunos.html`
- [ ] `usuario/formularioUsuario.html`

**Padrão a seguir:**
```html
<!-- Copiar do EXEMPLO-SEO-listaAlunos.html -->
1. Meta tags no <head>
2. Breadcrumbs antes de <main>
3. Semântica HTML5
4. JSON-LD apropriado
5. Footer com schema
```

---

### 7. Submeter ao Google Search Console
**Status:** 🔑 CRÍTICO (Indexação)

**Passos:**
1. [ ] Acessar https://search.google.com/search-console
2. [ ] Cliar conta Google (se necessário)
3. [ ] Adicionar propriedade do site
4. [ ] Escolher método de verificação:
   - [ ] TXT record (DNS) - Mais seguro
   - [ ] Meta tag HTML
   - [ ] Upload arquivo HTML
   - [ ] Google Analytics
5. [ ] Verificar propriedade
6. [ ] Enviar sitemap.xml:
   - [ ] https://seu-site.com/sitemap.xml
   - [ ] https://seu-site.com/sitemap-index.xml
7. [ ] Monitorar em "Cobertura" > Erros
8. [ ] Verificar "Melhorias" > Mobile Usability

---

### 8. Configurar Google Analytics 4
**Status:** 📊 RECOMENDADO (Análise)

1. [ ] Criar conta em https://analytics.google.com
2. [ ] Criar propriedade
3. [ ] Gerar tag GA4 (formato: G-XXXXXXXXXX)
4. [ ] Adicionar ao `<head>` de todas as páginas:

```html
<!-- Google Analytics 4 -->
<script async src="https://www.googletagmanager.com/gtag/js?id=G-XXXXXXXXXX"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'G-XXXXXXXXXX');
</script>
```

5. [ ] Testar instalação em Google Analytics
6. [ ] Configurar objetivos/conversões
7. [ ] Acompanhar tráfego

---

### 9. Implementar Sitemap Dinâmico
**Status:** 💾 AVANÇADO (Banco de dados)

Atualizar `SeoController.java` para consultar banco de dados:

```java
@Autowired
private AlunoRepository alunoRepository;

@GetMapping("/sitemap-alunos.xml")
public ResponseEntity<String> sitemapAlunos() {
    StringBuilder xml = new StringBuilder();
    xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    xml.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
    
    // Adicionar listagem
    addUrlEntry(xml, "/alunos/listar", "daily", "0.9", getIsoNow());
    
    // Adicionar alunos individuais
    List<Aluno> alunos = alunoRepository.findAll();
    alunos.forEach(aluno -> 
        addUrlEntry(xml, "/alunos/" + aluno.getId(), "weekly", "0.7", getIsoNow())
    );
    
    xml.append("</urlset>");
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(xml.toString());
}
```

**Para cada recurso:**
- [ ] Alunos
- [ ] Cursos
- [ ] Disciplinas
- [ ] Professores
- [ ] Pedidos
- [ ] Produtos

---

### 10. Testar SEO com Ferramentas
**Status:** 🧪 RECOMENDADO

**Ferramentas Grátis:**

- [ ] **PageSpeed Insights** (https://pagespeed.web.dev)
  - Medir performance
  - Otimizar Core Web Vitals
  
- [ ] **Mobile-Friendly Test** (https://search.google.com/test/mobile-friendly)
  - Verificar responsividade
  
- [ ] **Structured Data Testing Tool** (https://search.google.com/test/rich-results)
  - Validar JSON-LD
  - Verificar rich snippets
  
- [ ] **Meta Tags Preview** (https://metatags.io)
  - Visualizar como aparece em SERPs
  - Verificar OG tags
  
- [ ] **WAVE** (https://wave.webaim.org)
  - Acessibilidade web
  - Estrutura semântica

**Ferramentas Pagas (Premium):**
- [ ] Semrush
- [ ] Ahrefs
- [ ] Moz Pro

---

## 📝 RESUMO DE STATUS

```
Implementação Automática: ✅ 100%
├─ Meta tags: ✅
├─ Open Graph: ✅
├─ Twitter Cards: ✅
├─ JSON-LD: ✅
├─ Robots.txt: ✅
├─ Sitemap.xml: ✅
├─ Breadcrumbs: ✅
└─ CSS: ✅

Configuração Manual: ⏳ PENDENTE
├─ Substituir domínio: [ ]
├─ Criar favicon: [ ]
├─ Criar OG image: [ ]
├─ Compressão gzip: [ ]
├─ Cache HTTP: [ ]
├─ Aplicar a todas páginas: [ ]
├─ Google Search Console: [ ]
├─ Google Analytics 4: [ ]
├─ Sitemap dinâmico: [ ]
└─ Testes: [ ]
```

---

## 🚀 Próximos Passos Imediatos

1. **HOJE:** Substituir domínio em todos os arquivos
2. **AMANHÃ:** Criar favicon + OG image
3. **ESTA SEMANA:** Aplicar SEO a todas as páginas
4. **ANTES DE PUBLICAR:** Submeter ao Google Search Console
5. **APÓS PUBLICAÇÃO:** Monitorar GSC + Analytics

---

## 📞 Contato & Suporte

- **Google SEO Docs:** https://developers.google.com/search
- **Schema.org:** https://schema.org
- **Documentação Thymeleaf:** https://www.thymeleaf.org
- **Spring Boot:** https://spring.io/projects/spring-boot

---

**Última atualização:** 23 de maio de 2026  
**Desenvolvido por:** Sistema Inovatech  
**Status:** ✅ Implementação Completa

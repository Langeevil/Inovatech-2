# 📚 Guia SEO - Sistema Inovatech

## 🚀 Implementações de Encontrabilidade (SEO)

Este guia documenta todos os recursos de SEO (Search Engine Optimization) implementados no Sistema Inovatech para melhorar a encontrabilidade e o ranking nos mecanismos de busca.

---

## ✅ O que foi Implementado

### 1. **Meta Tags Essenciais** ✓
Adicionadas em todas as páginas principais:
- `<meta name="description">` - Descrição para SERPs (até 160 caracteres)
- `<meta name="keywords">` - Palavras-chave relevantes
- `<meta name="viewport">` - Responsividade mobile
- `<meta name="theme-color">` - Cor da barra mobile
- `<link rel="icon">` - Favicon do site

**Localização:** `src/main/resources/templates/*.html`

---

### 2. **Open Graph Tags (OG)** ✓
Controla como o site aparece em redes sociais (Facebook, LinkedIn, etc.):
```html
<meta property="og:title" content="...">
<meta property="og:description" content="...">
<meta property="og:image" content="...">
<meta property="og:url" content="...">
<meta property="og:type" content="website">
```

**Por que:** Quando alguém compartilha seu site, aparece com título, descrição e imagem profissionais.

---

### 3. **Twitter Cards** ✓
Otimização para compartilhamento no Twitter/X:
```html
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:title" content="...">
<meta name="twitter:description" content="...">
```

---

### 4. **JSON-LD Schema (Dados Estruturados)** ✓
Ajuda o Google a entender seu conteúdo:

#### Organization Schema
```json
{
  "@context": "https://schema.org",
  "@type": "Organization",
  "name": "Inovatech",
  "url": "https://seu-site.com",
  "logo": "https://seu-site.com/logo.png"
}
```

#### BreadcrumbList Schema
```json
{
  "@type": "BreadcrumbList",
  "itemListElement": [
    {"@type": "ListItem", "position": 1, "name": "Home"},
    {"@type": "ListItem", "position": 2, "name": "Alunos"}
  ]
}
```

#### WebPage Schema
```json
{
  "@type": "WebPage",
  "name": "Nome da Página",
  "description": "Descrição da página"
}
```

**Impacto:** Ativa Rich Snippets no Google (estrelas, avaliações, etc.)

---

### 5. **Canonical Tags** ✓
Evita problemas de conteúdo duplicado:
```html
<link rel="canonical" href="https://seu-site.com/pagina">
```

**Por que:** Indica ao Google qual é a "versão oficial" de uma página.

---

### 6. **Breadcrumbs (Migalhas de Pão)** ✓
Navegação visual + Schema.org estruturado:

**HTML:**
```html
<nav aria-label="Breadcrumb">
  <ol class="breadcrumb-list">
    <li><a href="/">Home</a></li>
    <li><a href="/alunos">Alunos</a></li>
    <li aria-current="page">Listar Alunos</li>
  </ol>
</nav>
```

**Benefícios:**
- Melhora navegação do usuário
- Aparece no Google com estrutura clara
- Reduz taxa de rejeição

**Localização:** `src/main/resources/templates/fragments/breadcrumbs.html`

---

### 7. **Robots.txt** ✓
Arquivo que controla o rastreamento de bots:

**Localização:** `src/main/resources/static/robots.txt`

**O que faz:**
- Define quais áreas podem ser rastreadas
- Bloqueia páginas privadas (`/admin`, `/login`, etc.)
- Aponta para os sitemaps

```
User-agent: *
Allow: /
Disallow: /admin
Disallow: /login
Sitemap: https://seu-site.com/sitemap.xml
```

---

### 8. **Sitemap XML** ✓
Mapa de todas as URLs do site para indexação pelo Google:

**Endpoints Implementados:**
- `/sitemap.xml` - Sitemap principal
- `/sitemap-index.xml` - Índice de sitemaps
- `/sitemap-alunos.xml` - Páginas de alunos
- `/sitemap-cursos.xml` - Páginas de cursos
- `/sitemap-disciplinas.xml` - Páginas de disciplinas
- `/sitemap-professores.xml` - Páginas de professores

**Exemplo de entrada:**
```xml
<url>
  <loc>https://seu-site.com/alunos/listar</loc>
  <lastmod>2026-05-23T10:30:00+00:00</lastmod>
  <changefreq>daily</changefreq>
  <priority>0.9</priority>
</url>
```

**Localização:** `src/main/java/br/com/faculdadeinovatech/inovatech2/controller/SeoController.java`

---

### 9. **HTML Semântico** ✓
Uso de tags HTML5 que indicam semântica:

**Antes (ruim):**
```html
<div class="container">
  <div class="article">Conteúdo</div>
</div>
```

**Depois (bom):**
```html
<main class="container" role="main">
  <article>Conteúdo</article>
</main>
```

**Tags Semânticas Utilizadas:**
- `<main>` - Conteúdo principal
- `<article>` - Conteúdo de artigo
- `<section>` - Seção de conteúdo
- `<nav>` - Navegação
- `<footer>` - Rodapé
- `<header>` - Cabeçalho
- `<h1>` a `<h6>` - Hierarquia de títulos

---

### 10. **Microdata (Itemprop)** ✓
Adiciona metadata estruturada diretamente no HTML:

```html
<article itemscope itemtype="https://schema.org/Article">
  <h1 itemprop="headline">Título do Artigo</h1>
  <p itemprop="description">Descrição</p>
  <span itemprop="author">Autor</span>
</article>
```

---

### 11. **CSS para Breadcrumbs** ✓
Estilo visual profissional para navegação:

**Localização:** `src/main/resources/static/css/style.css` (final do arquivo)

```css
.breadcrumb-nav {
    background: var(--surface-color);
    border-bottom: 1px solid var(--border-color);
    padding: 12px 20px;
}

.breadcrumb-item a {
    color: var(--primary-color);
    text-decoration: none;
}
```

---

### 12. **Fragmentos Reutilizáveis (Thymeleaf)** ✓
Componentes centralizados para facilitar manutenção:

**Localização:** `src/main/resources/templates/fragments/`

- `seo.html` - Meta tags, Open Graph, Twitter Cards, JSON-LD
- `breadcrumbs.html` - Navegação com schema
- `semantic.html` - Elementos semânticos reutilizáveis

**Como usar:**
```html
<!-- Importar fragmento -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <!-- Meta tags SEO -->
    <th:block th:replace="fragments/seo :: meta-description('Descrição personalizada')"></th:block>
</head>
</html>
```

---

### 13. **Otimização de Títulos** ✓
Títulos descritivos em cada página:

- **Página inicial:** "Sistema de Gerenciamento Acadêmico - Inovatech"
- **Painel:** "Painel do Usuário - Sistema Inovatech"
- **Login:** "Login - Sistema Inovatech"

**Fórmula recomendada:** `[Página] - [Marca]`

---

### 14. **URLs Amigáveis** ✓
Estrutura atual já é SEO-friendly:
- `/alunos/listar` ✓
- `/cursos/listar` ✓
- `/professores/listar` ✓

---

## 🔧 Como Usar e Estender

### Adicionar SEO a uma nova página

1. **Importe os fragmentos no `<head>`:**
```html
<!DOCTYPE html>
<html lang="pt-br" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/x-icon" href="/favicon.ico">
    
    <!-- Meta tags dinâmicas -->
    <title>Meu Título - Sistema Inovatech</title>
    <meta name="description" content="Minha descrição aqui">
    <meta name="keywords" content="palavras, chave">
    
    <!-- JSON-LD Schema -->
    <script type="application/ld+json">
    {
        "@context": "https://schema.org",
        "@type": "WebPage",
        "name": "Nome da Página"
    }
    </script>
</head>
<body>
    <!-- Breadcrumbs -->
    <th:block th:replace="fragments/breadcrumbs :: breadcrumb(${breadcrumbItems})"></th:block>
    
    <!-- Conteúdo principal com semântica -->
    <main role="main">
        <article>
            <h1>Título</h1>
            <p>Descrição</p>
        </article>
    </main>
</body>
</html>
```

### Atualizar Sitemap

Quando adicionar novas páginas, adicione-as ao `SeoController.java`:

```java
@GetMapping("/sitemap.xml")
public ResponseEntity<String> sitemap() {
    // ... código existente ...
    addUrlEntry(xml, "/sua-nova-pagina", "weekly", "0.7", getIsoNow());
}
```

### Configurar domínio real

Substitua `https://seu-site.com` em todos os arquivos pelo seu domínio:
- `index.html`
- `home.html`
- `login.html`
- `SeoController.java`
- `robots.txt`

---

## 📊 Próximas Etapas Recomendadas

### 1. **Criar Favicon**
- Gerar favicon.ico em `src/main/resources/static/favicon.ico`
- Ferramentas: favicon-generator.org, realfavicongenerator.net

### 2. **Criar Imagem OG**
- Criar imagem 1200x630px para compartilhamento em redes sociais
- Localizar em: `src/main/resources/static/og-image.png`

### 3. **Adicionar Dinâmica ao Sitemap**
Implementar consultas ao banco de dados:
```java
@Autowired
private AlunoRepository alunoRepository;

@GetMapping("/sitemap-alunos.xml")
public ResponseEntity<String> sitemapAlunos() {
    List<Aluno> alunos = alunoRepository.findAll();
    alunos.forEach(aluno -> 
        addUrlEntry(xml, "/alunos/" + aluno.getId(), "weekly", "0.7", ...)
    );
}
```

### 4. **Implementar Compressão Gzip**
Adicionar ao `application.properties`:
```properties
server.compression.enabled=true
server.compression.min-response-size=1024
```

### 5. **Submeter ao Google Search Console**
1. Acessar https://search.google.com/search-console
2. Adicionar propriedade do site
3. Enviar sitemap.xml
4. Monitorar indexação e erros

### 6. **Otimizar Core Web Vitals**
- Medir com PageSpeed Insights
- Otimizar LCP (Largest Contentful Paint)
- Reduzir CLS (Cumulative Layout Shift)
- Melhorar FID (First Input Delay)

### 7. **Adicionar Alt Text a Imagens**
```html
<img src="aluno.jpg" alt="Descrição clara da imagem" title="Tooltip">
```

### 8. **Implementar HTTPS**
- Certificado SSL/TLS obrigatório
- Usar redirecionamento HTTP → HTTPS

---

## 📈 Métricas de SEO a Monitorar

1. **Indexação:** Quantas páginas estão no Google
2. **Impressões:** Vezes que seu site aparece nos resultados
3. **Cliques:** Vezes que usuários clicam no seu link
4. **CTR:** Taxa de cliques (impressões vs cliques)
5. **Posição Média:** Ranking médio nos resultados
6. **Cobertura:** Erros de rastreamento e indexação
7. **Mobile Usability:** Problemas em dispositivos móveis

**Ferramentas:**
- Google Search Console
- Google Analytics 4
- Semrush
- Ahrefs
- Moz

---

## 🔐 Checklist de SEO Finalizado

- ✅ Meta tags essenciais (description, keywords, viewport)
- ✅ Open Graph tags (social media)
- ✅ Twitter Cards
- ✅ JSON-LD schemas (Organization, WebPage, BreadcrumbList)
- ✅ Canonical tags
- ✅ Robots.txt
- ✅ Sitemap.xml (dinâmico)
- ✅ Breadcrumbs com schema
- ✅ HTML semântico
- ✅ Microdata (itemprop)
- ✅ Títulos otimizados
- ✅ URLs amigáveis
- ✅ CSS para UX

---

## 📞 Suporte

Para dúvidas sobre SEO, consulte:
- Google Search Central: https://developers.google.com/search
- Schema.org Documentação: https://schema.org
- Moz Learning Center: https://moz.com/learn/seo
- Semrush Blog: https://www.semrush.com/blog/

---

**Última atualização:** 23 de maio de 2026
**Desenvolvido por:** Sistema Inovatech

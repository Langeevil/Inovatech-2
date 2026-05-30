package br.com.faculdadeinovatech.inovatech2.controller;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controller responsável pelos recursos de SEO (Encontrabilidade)
 * - Sitemap.xml: Mapa de índice de todas as URLs do site
 * - RSS Feed: Para feeds de notícias/atualizações
 */
@RestController
public class SeoController {

    private static final String SITE_URL = "https://seu-site.com"; // TODO: Configurar como propriedade
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+00:00");

    /**
     * Gera o sitemap.xml dinamicamente
     * Este sitemap lista todas as URLs principais do site para indexação pelo Google
     * 
     * @return XML do sitemap com informações de lastmod, changefreq e priority
     */
    @GetMapping("/sitemap.xml")
    public ResponseEntity<String> sitemap() {
        StringBuilder xml = new StringBuilder();
        
        // Cabeçalho XML
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"\n");
        xml.append("        xmlns:mobile=\"http://www.google.com/schemas/sitemap-mobile/1.0\">\n\n");

        // URLs estáticas principais
        addUrlEntry(xml, "/", "weekly", "1.0", getIsoNow());
        addUrlEntry(xml, "/login", "monthly", "0.7", getIsoNow());

        // URLs de listagem - Recursos principais
        addUrlEntry(xml, "/alunos/listar", "daily", "0.9", getIsoNow());
        addUrlEntry(xml, "/cursos/listar", "daily", "0.9", getIsoNow());
        addUrlEntry(xml, "/disciplinas/listar", "daily", "0.8", getIsoNow());
        addUrlEntry(xml, "/professores/listar", "daily", "0.8", getIsoNow());
        addUrlEntry(xml, "/pedidos/listar", "daily", "0.8", getIsoNow());
        addUrlEntry(xml, "/produtos/listar", "weekly", "0.7", getIsoNow());
        addUrlEntry(xml, "/relatorios/alunos", "weekly", "0.7", getIsoNow());

        // URLs de criação/formulários
        addUrlEntry(xml, "/alunos/criar", "monthly", "0.6", getIsoNow());
        addUrlEntry(xml, "/cursos/criar", "monthly", "0.6", getIsoNow());
        addUrlEntry(xml, "/disciplinas/criar", "monthly", "0.6", getIsoNow());
        addUrlEntry(xml, "/professores/criar", "monthly", "0.6", getIsoNow());

        xml.append("</urlset>");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                        ContentDisposition.inline().filename("sitemap.xml").build().toString())
                .body(xml.toString());
    }

    /**
     * Gera um sitemap index para quando houver múltiplos sitemaps
     * Útil quando o site tem muitas URLs (> 50.000)
     */
    @GetMapping("/sitemap-index.xml")
    public ResponseEntity<String> sitemapIndex() {
        StringBuilder xml = new StringBuilder();
        
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<sitemapindex xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
        
        // Sitemaps por tipo de recurso
        addSitemapEntry(xml, "/sitemap.xml");
        addSitemapEntry(xml, "/sitemap-alunos.xml");
        addSitemapEntry(xml, "/sitemap-cursos.xml");
        addSitemapEntry(xml, "/sitemap-disciplinas.xml");
        addSitemapEntry(xml, "/sitemap-professores.xml");
        
        xml.append("</sitemapindex>");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(xml.toString());
    }

    /**
     * Sitemap específico para alunos (para futuro - quando houver muitos registros)
     * TODO: Implementar para consultar banco de dados dinamicamente
     */
    @GetMapping("/sitemap-alunos.xml")
    public ResponseEntity<String> sitemapAlunos() {
        StringBuilder xml = new StringBuilder();
        
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
        
        // Página de listagem
        addUrlEntry(xml, "/alunos/listar", "daily", "0.9", getIsoNow());
        
        // TODO: Adicionar URLs individuais de alunos do banco de dados
        // List<Aluno> alunos = alunoService.findAll();
        // alunos.forEach(aluno -> addUrlEntry(xml, "/alunos/" + aluno.getId(), "weekly", "0.7", ...));
        
        xml.append("</urlset>");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(xml.toString());
    }

    /**
     * Sitemap para cursos
     */
    @GetMapping("/sitemap-cursos.xml")
    public ResponseEntity<String> sitemapCursos() {
        StringBuilder xml = new StringBuilder();
        
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
        
        addUrlEntry(xml, "/cursos/listar", "daily", "0.9", getIsoNow());
        // TODO: Adicionar URLs individuais de cursos do banco de dados
        
        xml.append("</urlset>");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(xml.toString());
    }

    /**
     * Sitemap para disciplinas
     */
    @GetMapping("/sitemap-disciplinas.xml")
    public ResponseEntity<String> sitemapDisciplinas() {
        StringBuilder xml = new StringBuilder();
        
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
        
        addUrlEntry(xml, "/disciplinas/listar", "daily", "0.8", getIsoNow());
        // TODO: Adicionar URLs individuais de disciplinas do banco de dados
        
        xml.append("</urlset>");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(xml.toString());
    }

    /**
     * Sitemap para professores
     */
    @GetMapping("/sitemap-professores.xml")
    public ResponseEntity<String> sitemapProfessores() {
        StringBuilder xml = new StringBuilder();
        
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
        
        addUrlEntry(xml, "/professores/listar", "daily", "0.8", getIsoNow());
        // TODO: Adicionar URLs individuais de professores do banco de dados
        
        xml.append("</urlset>");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(xml.toString());
    }

    // ============= Métodos auxiliares =============

    /**
     * Adiciona uma entrada de URL ao sitemap XML
     * 
     * @param xml StringBuilder do XML
     * @param path Caminho relativo da URL (ex: "/alunos/listar")
     * @param changefreq Frequência de mudança (always, hourly, daily, weekly, monthly, yearly, never)
     * @param priority Prioridade relativa (0.0 a 1.0)
     * @param lastmod Data da última modificação (ISO 8601)
     */
    private void addUrlEntry(StringBuilder xml, String path, String changefreq, String priority, String lastmod) {
        xml.append("  <url>\n");
        xml.append("    <loc>").append(SITE_URL).append(path).append("</loc>\n");
        xml.append("    <lastmod>").append(lastmod).append("</lastmod>\n");
        xml.append("    <changefreq>").append(changefreq).append("</changefreq>\n");
        xml.append("    <priority>").append(priority).append("</priority>\n");
        xml.append("  </url>\n");
    }

    /**
     * Adiciona uma entrada de sitemap ao sitemap index
     */
    private void addSitemapEntry(StringBuilder xml, String path) {
        xml.append("  <sitemap>\n");
        xml.append("    <loc>").append(SITE_URL).append(path).append("</loc>\n");
        xml.append("    <lastmod>").append(getIsoNow()).append("</lastmod>\n");
        xml.append("  </sitemap>\n");
    }

    /**
     * Retorna a data/hora atual em formato ISO 8601
     */
    private String getIsoNow() {
        return LocalDateTime.now().format(ISO_FORMATTER);
    }
}

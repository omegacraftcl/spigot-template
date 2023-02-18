package com.claudiobo.template.daos;

public class TemplateDAO {

    // public static void createTable() {
    //     Connection conn = Main.getInstance().getSqlConnection().getConnection();
    //     String query1 = "CREATE TABLE IF NOT EXISTS Templates (" + "id INT AUTO_INCREMENT PRIMARY KEY," + "uuid VARCHAR(64) NOT NULL," + "display_name VARCHAR(16) NOT NULL," + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," + "INDEX idx_uuid(uuid));";
    //     try (Statement stmt = conn.createStatement()) {
    //         stmt.executeUpdate(query1);
    //     } catch (Exception e) {
    //         Main.getInstance().getLogger().severe("Error al ejecutar 'createTable': " + e.getMessage());
    //     }
    // }

    // public static boolean createTemplate(Template template) {
    //     Connection conn = Main.getInstance().getSqlConnection().getConnection();
    //     String query = "INSERT INTO templates (uuid, display_name, updated_at) VALUES (?, ?, ?);";
    //     try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
    //         stmt.setString(1, template.getUuid());
    //         stmt.setString(2, template.getDisplayName());
    //         stmt.setDate(3, new java.sql.Date(template.getUpdatedAt().getTime()));

    //         int generado = stmt.executeUpdate();
    //         try (ResultSet rs = stmt.getGeneratedKeys()) {
    //             if (rs.next()) {
    //                 template.setId(rs.getInt(1));
    //             }
    //         } catch (Exception e) {
    //             Main.getInstance().getLogger().severe("Error al ejecutar 'createTemplate': " + e.getMessage());
    //             Main.getInstance().getLogger().severe("Objecto en cuestión: " + template.toString());
    //             throw new DatabaseException("Error al ejecutar 'createTemplate': " + e.getMessage());
    //         }

    //         return generado != 0;
    //     } catch (DatabaseException e) {
    //         throw e;
    //     } catch (Exception e) {
    //         Main.getInstance().getLogger().severe("Error al ejecutar 'createTemplate': " + e.getMessage());
    //         Main.getInstance().getLogger().severe("Objecto en cuestión: " + template.toString());
    //         throw new DatabaseException("Error al ejecutar 'createTemplate': " + e.getMessage());
    //     }
    // }

    // public static Template fetchTemplate(Player p) {
    //     Connection conn = Main.getInstance().getSqlConnection().getConnection();
    //     String query = "SELECT id, updated_at FROM Templates WHERE uuid = ? LIMIT 1;";
    //     try (PreparedStatement stmt = conn.prepareStatement(query)) {
    //         String uuid = p.getUniqueId().toString();
    //         stmt.setString(1, uuid);
    //         try (ResultSet rs = stmt.executeQuery()) {
    //             if (rs.next()) {
    //                 Template Template = new Template();
    //                 Template.setId((rs.getInt(1)));
    //                 Template.setTemplates((rs.getInt(2)));
    //                 Template.setUpdatedAt((rs.getDate(3)));
    //                 Template.setPlayer(p);
    //                 Template.setUuid(uuid);
    //                 Template.setDisplayName(p.getName());
    //                 Template.setLastFetchedAt(new Date());
    //                 return Template;
    //             }
    //         } catch (Exception e) {
    //             Main.getInstance().getLogger().severe("Error al ejecutar 'loadTemplate': " + e.getMessage());
    //             Main.getInstance().getLogger().severe("Objecto en cuestión: " + p.getUniqueId().toString());
    //             throw new DatabaseException("Error al ejecutar 'loadTemplate': " + e.getMessage());
    //         }
    //     } catch (DatabaseException e) {
    //         throw e;
    //     } catch (Exception e) {
    //         Main.getInstance().getLogger().severe("Error al ejecutar 'loadTemplate': " + e.getMessage());
    //         Main.getInstance().getLogger().severe("Objecto en cuestión: " + p.getUniqueId().toString());
    //         throw new DatabaseException("Error al ejecutar 'loadTemplate': " + e.getMessage());
    //     }
    //     return null;
    // }

    // public static boolean updateTemplate(Template Template) {
    //     Connection conn = Main.getInstance().getSqlConnection().getConnection();
    //     String query = "UPDATE Templates SET display_name = ?, Templates = ?, updated_at = ? WHERE id = ?;";
    //     try (PreparedStatement stmt = conn.prepareStatement(query)) {
    //         stmt.setString(1, Template.getDisplayName());
    //         stmt.setInt(2, Template.getTemplates());
    //         stmt.setDate(3, new java.sql.Date(Template.getUpdatedAt().getTime()));
    //         stmt.setInt(4, Template.getId());
    //         int actualizado = stmt.executeUpdate();
    //         return actualizado != 0;
    //     } catch (Exception e) {
    //         Main.getInstance().getLogger().severe("Error al ejecutar 'updateTemplate': " + e.getMessage());
    //         Main.getInstance().getLogger().severe("Objecto en cuestión: " + Template.toString());
    //         throw new DatabaseException("Error al ejecutar 'updateTemplate': " + e.getMessage());
    //     }
    // }


}

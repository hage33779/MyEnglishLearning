package myEnglishLearning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAO {
    public List<Word> getAllWords() {
        List<Word> words = new ArrayList<>();
        String sql = "SELECT * FROM words";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Word word = new Word();
                word.setId(rs.getInt("id"));
                word.setName(rs.getString("name"));
                word.setExplanation(rs.getString("explanation"));
                words.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return words;
    }

    // 単語を削除
    public boolean deleteWord(int idToDelete) {
        String sql = "DELETE FROM words WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idToDelete);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 単語を追加
    public boolean addWord(Word newWord) {
        String sql = "INSERT INTO words (name, explanation) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newWord.getName());
            stmt.setString(2, newWord.getExplanation());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

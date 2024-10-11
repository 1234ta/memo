package com.example.memo_app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo,Long> {

    // タイトル又は内容にキーワードが含まれるメモを検索する。
    @Query("SELECT m FROM Memo m WHERE m.title LIKE CONCAT('%', :keyword, '%') OR m.content LIKE CONCAT('%', :keyword, '%')")
    List<Memo> searchMemos(@Param("keyword") String keyword);
}
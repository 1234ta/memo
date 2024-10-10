package com.example.memo_app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/memos")
public class MemoController {
    
    @Autowired
    private MemoRepository memoRepository;

    //メモの一覧表示
    @GetMapping
    public String listMemos(Model model) {
        List<Memo> memos = memoRepository.findAll();
        model.addAttribute("memos", memos);
        return "memo-list";
    }

    //新しいメモのフォーム表示
    @GetMapping("/new")
    public String newMemoForm(Model model){
        model.addAttribute("memo",new Memo());
        return "memo-form";
    }

    //新しいメモの作成
    @PostMapping
    public String saveMemo(@ModelAttribute Memo memo) {
        memoRepository.save(memo);
        return "redirect:/memos";
    }
    
    //メモの編集フォームの表示
    @GetMapping("/edit/{id}")
    public String editMemoForm(@PathVariable Long id, Model model) {
        Memo memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid memo Id:" + id));
        model.addAttribute("memo", memo);
        return "memo-form";
    }

    //メモの削除
    @GetMapping("/delete/{id}")
    public String deleteMemo(@PathVariable Long id) {
        memoRepository.deleteById(id);        
        return "redirect:/memos";
    }
}

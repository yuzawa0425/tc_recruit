package jp.co.tc.recruit.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jp.co.tc.recruit.entity.User;
import jp.co.tc.recruit.form.UserForm;
import jp.co.tc.recruit.repository.MessageStatusRepository;
import jp.co.tc.recruit.service.MessageStatusService;
import jp.co.tc.recruit.service.SelectionStatusDetailService;
import jp.co.tc.recruit.service.SelectionStatusService;
import jp.co.tc.recruit.service.UserService;

/**
 * マスタメンテナンスのコントローラー
 */
@Controller
@RequestMapping("/maintenance")
public class MasterMaintenanceController {

	@Autowired
	SelectionStatusService slcSttSvc;
	@Autowired
	SelectionStatusDetailService slcSttDtlSvc;
	@Autowired
	MessageStatusService msgSttSvc;
	@Autowired
	MessageStatusRepository msgSttRepo;
	@Autowired
	UserService usrSvc;

	/**
	 * メンテナンス可能項目の表示
	 *
	 * @param model
	 * @return マスタメンテナンス一覧
	 */
	@GetMapping()
	public String getMaintenance(Model model) {
		return "/master_maintenance";
	}

	/**
	 * 社員マスタの検索、表示
	 *
	 * @return 社員マスタメンテナンス画面
	 */
	@GetMapping("user")
	public String userUpdateInput(
			@ModelAttribute("User") UserForm userForm, Model model) {
		List<User> usrList;
		usrList = usrSvc.findAllByOrderByUsername();
		model.addAttribute("usrList", usrList);
		return "master_maintenance/user";
	}

	/**
	 * 社員マスタの一括更新
	 *
	 * @param msgSttForm
	 * @return 社員マスタメンテナンス画面
	 */
	@PostMapping("user/update")
	@Transactional(readOnly = false)
	public String userUpdate(
			@ModelAttribute("User") UserForm userForm, Model model) {
		usrSvc.userUpdate(userForm);
		return "redirect:/maintenance/user";
	}

	/**
	 * 社員マスタの一括更新
	 *
	 * @param msgSttForm
	 * @return 社員マスタメンテナンス画面
	 */
	@PostMapping("sample/upload")
	public String upload(@RequestParam("namelist.csv") MultipartFile multipartFile, Model model) {

		 try {
		      File file = new File("C:\\");
		      FileReader filereader = new FileReader(file);
		      int ch;
		      while((ch = filereader.read()) != -1){
		        System.out.print((char)ch);
		      }
		      filereader.close();
		    } catch(FileNotFoundException e) {
		      System.out.println(e);
		    } catch(IOException e) {
		      System.out.println(e);
		    }

		return"redirect:/maintenance/user";
}

}
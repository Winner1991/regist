package message;
import org.springframework.stereotype.Controller;
/**
 * ¿ØÖÆ×é¼þ
 * */
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class UserController {
	@RequestMapping("reg.do")
	public String reg(){
		System.out.println("UserController.reg()");
		return "reg";
	}
	@RequestMapping("handle_reg.do")
	public String log(){
		System.out.println("UserController.reg()");
		return "login";
	}
	@RequestMapping("handle_login.do")
	public String login(){
		System.out.println("UserController.mes()");
		return "index";
	}
	@RequestMapping("message.do")
	public String satrt(){
		System.out.println("UserController.reg()");
		return "message";
	}
}

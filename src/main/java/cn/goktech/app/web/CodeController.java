package cn.goktech.app.web;

import cn.goktech.app.common.ResponseBean;
import cn.goktech.app.common.VerificationCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Jian Yueting
 */
@RestController
public class CodeController {
    private static final String SIGNED_CODE = "signedCode";

    @GetMapping("/code")
    public void code(HttpSession session, HttpServletResponse response) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();

        session.setAttribute(SIGNED_CODE, code.getText());

        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        try (OutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(image, "jpeg", outputStream);
            outputStream.flush();
        }
    }

    @GetMapping("/verify")
    public ResponseBean verify(HttpSession session, String code) {
        if (StringUtils.isBlank(code)) {
            return ResponseBean.ErrorBean("验证码未输入");
        }

        String signedCode = (String) session.getAttribute(SIGNED_CODE);

        if (code.equalsIgnoreCase(signedCode)) {
            return ResponseBean.OkBean("验证成功");
        }

        return ResponseBean.ErrorBean("验证失败");
    }
}

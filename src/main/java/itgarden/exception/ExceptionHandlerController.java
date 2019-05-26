/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.exception;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Md Belayet Hossin
 */
@ControllerAdvice
public class ExceptionHandlerController {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//
//    public @ResponseBody
//    ExceptionResponse handleResourceNotFound(final ResourceNotFoundException exception,
//            final HttpServletRequest request) {
//
//        ExceptionResponse error = new ExceptionResponse();
//        error.setErrorMessage(exception.getMessage());
//        error.callerURL(request.getRequestURI());
//
//        return error;
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public @ResponseBody
//    ExceptionResponse handleException(final Exception exception,
//            final HttpServletRequest request) {
//
//        ExceptionResponse error = new ExceptionResponse();
//        error.setErrorMessage(exception.getMessage());
//        error.callerURL(request.getRequestURI());
//
//        return error;
//    }
    @ExceptionHandler(Exception.class)
    public String Notfound(Model model, HttpServletRequest request, Exception ex) {

        model.addAttribute("error","An error occurred, please try again later");
//        model.addAttribute("cause", ex.getCause());
//        model.addAttribute("message", ex.getMessage());
//        model.addAttribute("stacktrace", ex.getStackTrace());
        return "error/index";
    }

//    @ExceptionHandler(SQLException.class)
//    public String sql(Model model, HttpServletRequest request, Exception ex) {
//
////        model.addAttribute("error", ex.toString());
////        model.addAttribute("cause", ex.getCause());
////        model.addAttribute("message", ex.getMessage());
////        model.addAttribute("stacktrace", ex.getStackTrace());
//        return "error/index";
//    }

}

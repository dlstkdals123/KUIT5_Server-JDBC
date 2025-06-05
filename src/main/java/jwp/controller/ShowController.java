package jwp.controller;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();
        Question question = questionDao.findByQuestionId(Long.parseLong(req.getParameter("questionId")));
        req.setAttribute("question", question);
        req.setAttribute("answers", answerDao.findByQuestionId(Long.parseLong(req.getParameter("questionId"))));
        return "/qna/show.jsp";
    }
}

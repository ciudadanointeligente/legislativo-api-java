package cl.votainteligente.legislativo.servlet;

import cl.votainteligente.legislativo.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PersonServlet extends HttpServlet {

	private Gson gson;

	@Override
	public void init() {
		gson = new Gson();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String personId = request.getParameter("id");
			//Person person = Context.getPersonService().getPerson(Long.parseLong(personId));
			response.setStatus(HttpServletResponse.SC_OK);
			PrintWriter responseWriter = response.getWriter();
			response.setContentType("text/plain");
			//responseWriter.write(gson.toJson(person));
			responseWriter.close();
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
}

package M07_assignment1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve user inputs from the request parameters
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        double interestRate = Double.parseDouble(request.getParameter("interestRate"));
        int loanYears = Integer.parseInt(request.getParameter("loanYears"));

        // Compute the loan payments using the Loan class
        Loan loan = new Loan(interestRate, loanYears, loanAmount);
        double monthlyPayment = loan.getMonthlyPayment();
        double totalPayment = loan.getTotalPayment();

        // Set the response content type
        response.setContentType("text/html");
        
        // Get the locale from the request or use the default locale
        Locale locale = request.getLocale();
        // Format the loan payments using the locale
        String formattedMonthlyPayment = String.format(locale, "%.2f", monthlyPayment);
        String formattedTotalPayment = String.format(locale, "%.2f", totalPayment);

        // Generate the HTML response
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Loan Payment Results</title></head>");
        out.println("<body>");
        out.println("<h2>Loan Payment Results</h2>");
        out.println("<p>Monthly Payment: " + formattedMonthlyPayment + "</p>");
        out.println("<p>Total Payment: " + formattedTotalPayment + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}

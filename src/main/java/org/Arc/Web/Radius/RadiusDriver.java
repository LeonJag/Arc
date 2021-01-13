package org.Arc.Web.Radius;
import org.Arc.Web.ArcDriver;
import org.Arc.Web.Website;
import org.Arc.Client.Center;
import org.Arc.Client.Student;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RadiusDriver extends ArcDriver{
    public RadiusDriver() {
        super(Website.RADIUS);
        signIn();
    }

    public void signIn() {
        assert (getPage() == Website.RADIUS);

        Website radiusPage = this.getPage();
        String username = radiusPage.getUsername();
        String password = radiusPage.getPassword();

        WebElement signInField = this.findElement(By.id("UserName"));
        signInField.sendKeys(username);

        WebElement passwordField = this.findElement(By.id("Password"));
        passwordField.sendKeys(password);
        passwordField.submit();
    }

    public void search(String key) {
        assert (key != null && !key.isBlank() && !key.isEmpty());

        WebElement searchBtn = this.findElementById("SearchIcon");
        searchBtn.click();

        WebElement searchInput = this.findElementById("ContactSearch");
        searchInput.sendKeys(key);

        WebElement searchSumbit = this.findElementById("globalbtnsearch");
        searchSumbit.click();

        manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> searchResults = this.findElementsByClassName("linker");
        for (WebElement elem : searchResults)
            System.out.println(elem.getText());
    }
    
    public List<Student> searchStudent(String studentName) {
        get("https://radius.mathnasium.com/Student");

        manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement searchField = this.findElementById("StudentNameSearch");
        searchField.sendKeys(studentName);

        WebElement searchBtn = this.findElementById("btnsearch");
        searchBtn.click();
        manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        boolean resultsPresent = findElementsByCssSelector("#gridStudent > table > tbody > tr:nth-child(1)")
                .size() != 0;

        if (!resultsPresent)
            quit();

        int[] childIndices = { 1, 2, 3, 8, 10 };
        List<Student> studentResults = new ArrayList<>();

        int counter = 1;
        try {
            while (true) {
                String rowSelector = "#gridStudent > table > tbody > tr:nth-child(" + counter + ")";
                Student currStudent = new Student();
                for (int i : childIndices) {
                    String gridSelector = rowSelector + " > td:nth-child(" + i + ")";
                    WebElement currCell = findElementByCssSelector(gridSelector);
                    String info = currCell.getText();

                    switch (i) {
                        case 1:
                            WebElement linkElement = findElementByCssSelector(gridSelector + " > a");

                            String link = linkElement.getAttribute("href");
                            String ID = link.replace("https://radius.mathnasium.com/Student/Details/", "");

                            currStudent.setID(ID);
                            currStudent.setFirstName(info);
                            break;
                        case 2:
                            currStudent.setLastName(info);
                            break;
                        case 3:
                            if (info.equals("College"))
                                currStudent.setGrade(13);
                            else
                                currStudent.setGrade(Integer.parseInt(info));
                            break;
                        // case 8:
                        //     System.out.println(info);
                        //     break;
                        case 10:
                            currStudent.setCenter(Center.toCenter(info));
                            break;
                    }
                }
                studentResults.add(currStudent);
                counter += 1;
            }
        } catch (Throwable e) {
            return studentResults;
        }
        // Row Selector     #gridStudent > table > tbody > tr:nth-child(2)
        // Cell Selector    #gridStudent > table > tbody > tr.k-state-selected > td:nth-child(1)
    }

    public void openStudentProfile(Student s) {
        assert (!s.getID().equals(null));
        get("https://radius.mathnasium.com/Student/Details/" + s.getID());
    }

    public void openLearningPlan(Student s) {
        openStudentProfile(s);
        
    }

    public static void main(String[] args) {
        RadiusDriver rDriver = new RadiusDriver();
        List<Student> students = rDriver.searchStudent("Jessica");
        rDriver.openStudentProfile(students.get(0));
        
    }
}
package api.test;

import api.reports.ExternalReport;
import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.XLDataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class DataDrivenTest extends ExternalReport {
    User userData;

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = XLDataProvider.class, groups = {"smoke"})
    public void testPostUser(String id, String name, String job) throws FileNotFoundException {
        userData = new User();
        userData.setId(Integer.parseInt(id));
        userData.setName(name);
        userData.setJob(job);
        UserEndpoints.createUser(userData);

    }

    @Test(priority = 2, dataProvider = "UserId", dataProviderClass = XLDataProvider.class, groups = {"smoke"})
    public void testGetUser(String id) throws FileNotFoundException {
        userData = new User();
        userData.setId(Integer.parseInt(id));
        UserEndpoints.getUser(userData.getId());

    }

    @Test(priority = 3, dataProvider = "UserId", dataProviderClass = XLDataProvider.class, groups = {"smoke"})
    public void testUpdateUser(String id) throws FileNotFoundException {
        userData = new User();
        userData.setId(Integer.parseInt(id));
        this.userData.setName("Padmapriya");
        UserEndpoints.updateUser(userData);
    }

    @Test(priority = 4, dataProvider = "UserId", dataProviderClass = XLDataProvider.class, groups = {"smoke"})
    public void testDeleteUser(String id) throws FileNotFoundException {
        userData = new User();
        userData.setId(Integer.parseInt(id));
        UserEndpoints.deleteUser(userData.getId());
    }
}

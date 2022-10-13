package command;

import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registration extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;
    MessageDigest sha1;

    public Registration(String name, String description, boolean isArgument, MovieFactory movieFactory, RRHandler rrHandler) {
        super(isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
        try {
            this.sha1 = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void execute(ObjectForServer arg) {
        String[] args = arg.getArg().split(" ");
        String name = args[0];
        String password = args[1];
        byte[] bytesPassword = sha1.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder pass = new StringBuilder();
        for (byte b: bytesPassword){
            pass.append((String.format("%02X ", b)).replaceAll("\\s+",""));
        }
        String passwordHash = pass.toString();
        try {
            String req = "select exists(select * from users where name = ?)";
            PreparedStatement ps = movieFactory.getConnection().prepareStatement(req);
            ps.setString(1, arg.getArg());
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            if (resultSet.next() && !resultSet.getBoolean(1)) {
                String sql = "INSERT INTO users (name, password) values(?, ?)";
                PreparedStatement pStatement = movieFactory.getConnection().prepareStatement(sql);
                pStatement.setString(1, name);
                pStatement.setString(2, passwordHash);
                pStatement.execute();
                RRHandler.res(true);
            } else {
                RRHandler.res(false);
            }
        } catch (SQLException e) {
            RRHandler.res(false);
        }

    }
}

package utility;

import data.Movie;

import java.io.Serializable;

public class ObjectForServer implements Serializable {

    private final static long serialVersionUID = 1128932627584373707L;

    String command;
    String arg;
    String answer;
    String name;
    String password;
    Movie movie;
    boolean answerB;
    String login;

    public ObjectForServer(String command, String arg, String login) {
        this.command = command;
        this.arg = arg;
        this.login = login;
    }

//    public ObjectForServer(String command,String name, String password, String login) {
//        this.command = command;
//        this.name = name;
//        this.password = password;
//        this.login = login;
//    }

    public ObjectForServer(String command, Movie movie, String login) {
        this.command = command;
        this.movie = movie;
        this.login = login;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAnswerB() {
        return answerB;
    }

    public ObjectForServer(Boolean answerB) {
        this.answerB = answerB;
    }

    public ObjectForServer(String answer) {
        this.answer = answer;
    }

    public String getCommand() {
        return command;
    }

    public String getArg() {
        return arg;
    }

    public String getAnswer() {
        return answer;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCommandUser() {
        String commandUser;
        commandUser = command + arg;
        return commandUser;
    }

    @Override
    public String toString() {
        return "Ответ:\n" + answer;
    }
}

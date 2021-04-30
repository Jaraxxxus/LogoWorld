/**
 * <p> Command</p> Интерфейс для классов команд
 * содержит методы
 * <b>  execute </b>
 * <b> setArg(LogoWorld lw, String args)</b>
 * где <p>LogoWorld </p>- объект класса LogoWorld,
 * <p>args </p> передаваемые параметры команды в виде строки.
 * */


interface Command {
    void execute() ;
    void setArg(LogoWorld lw, String args);
}

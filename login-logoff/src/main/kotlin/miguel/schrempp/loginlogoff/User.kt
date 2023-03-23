package miguel.schrempp.loginlogoff

class User {
    val Username: String
    val Password: String
    val Email: String
    val Name: String
    var Logged: Boolean

    constructor(Username: String, Password: String, Email: String, Name: String, Logged: Boolean) {
        this.Username = Username
        this.Password = Password
        this.Email = Email
        this.Name = Name
        this.Logged = false
    }
}
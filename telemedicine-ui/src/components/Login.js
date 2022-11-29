import axios from 'axios';
import React from "react";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: null,
            password: null,
            authenticated: false,
        };
    }

    handleSubmit(e) {
        e.preventDefault()
        let payload = { username: this.state.username, password: this.state.password, userType: 'PATIENT' };
        axios.post('http://localhost:8080/login/authenticate', payload).then((response) => {
            this.setState({ authenticated: response.data });
        });

        console.log("Username: " + this.state.username + " Password: " + this.state.password
            + " --- authenticated? --- " + this.state.authenticated);
    };

    render() {
        if (!this.state.authenticated) {
            return <div>
                <form onSubmit={(e) => this.handleSubmit(e)}>
                    <input
                        type="text"
                        name="Username"
                        onChange={(e) => this.setState({ username: e.target.value })}
                    />
                    <input
                        type="password"
                        name="Password"
                        onChange={(e) => this.setState({ password: e.target.value })}
                    />
                    <input type="submit" value="Submit" />
                </form>
            </div>
        }
        return <h2>"Login Successful"</h2>;

    }
};

export default Login;
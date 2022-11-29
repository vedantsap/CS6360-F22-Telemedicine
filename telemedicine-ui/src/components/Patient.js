import axios from 'axios';
import React from "react";
import Table from './Table';

class Patient extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: null,
            password: null,
            authenticated: false,
            viewedProfile: false,

            userData: [],

            userId: null,
            fname: null,
            minit: null,
            lname: null,
            email: null
        };
    }

    getProfileHeadings() {
        // return Object.keys(this.state.userData[0]);
        return Object.keys({
            "userId": null,
            "password": null,
            "fname": null,
            "minit": null,
            "lname": null,
            "email": null
        });
    }

    togglePop = () => {
        if (!this.state.viewedProfile) {
            let payload = { username: this.state.username }
            axios.post('http://localhost:8080/superuser/getUserProfile', payload).then((response) => {
                console.log(response.data);
                this.setState({
                    userData: response.data,
                });
            });
            console.log(this.state.userData);
        }
        this.setState({
            viewedProfile: !this.state.viewedProfile
        });
    };

    handleLogin(e) {
        e.preventDefault()
        let payload = { username: this.state.username, password: this.state.password, userType: 'PATIENT' };
        axios.post('http://localhost:8080/login/authenticate', payload).then((response) => {
            this.setState({ authenticated: response.data });
        });

        console.log("Username: " + this.state.username + " Password: " + this.state.password
            + " --- authenticated? --- " + this.state.authenticated);
    };

    handleLogout(e) {
        e.preventDefault()
        this.setState({ authenticated: false })
    }

    render() {
        if (!this.state.authenticated) {
            return <div>
                <form onSubmit={(e) => this.handleLogin(e)}>
                    <h3>"Please login to the Patient's portal"</h3>
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
        return <div>
            <h3>"Welcome to the Patient's portal"</h3>
            <button onClick={(e) => this.handleLogout(e)}>Logout</button>
            <div>
                <div className="btn" onClick={this.togglePop}>
                    <button>View Profile</button>
                </div>
                {this.state.viewedProfile ?
                    <div className="modal">
                        <div className="modal_content">
                            <span className="close" onClick={() => this.toggle}>&times;</span>
                            <p>{this.state.fname}</p>
                            <Table theadData={this.getProfileHeadings()} tbodyData={this.state.userData} />
                        </div>
                    </div>
                    : null
                }
            </div>
        </div >;
    }
};

export default Patient;
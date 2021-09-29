import React, {Component} from "react";

export default class ValidationError extends Component {


    render(){
        if (!this.props.error) return null;
        return (
            <label className="small form-text text-danger">{this.props.error}</label>
        );
    }
}
import React, {Component} from "react";
import "../css/modern.css";

export default class Alert extends Component{


    render(){
        if (!this.props.message) return null;
        return (
            <div className="mb-3">
                <div className="alert alert-danger alert-dismissible" role="alert">
                    <div className="alert-icon">
                        <i className="fa fa-fw fa-exclamation"></i>
                    </div>
                    <div className="alert-message">
                        {this.props.message}
                    </div>
                    <button className="close" aria-label="Close" type="button" data-dismiss="alert">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
            </div>
        );
    }
}
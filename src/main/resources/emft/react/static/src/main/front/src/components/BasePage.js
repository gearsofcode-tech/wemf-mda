import React, { Component } from "react";
import "../css/bootstrap.css";
import "../css/modern.css";
import Header from './Header';
import {Link} from "react-router-dom";

class BasePage extends Component {

	

	render() {
    	return (
    		<div>
				<div className="wrapper">
					<Header/>
					<div className="main">
						<main id="main" className="content">
							<div className="container-fluid">
								<div className="header">
									<h1 className="header-title">
										{this.pageTitle()}
									</h1>
								</div>
								{this.renderPage()}
							</div>
						</main>
					</div>
				 </div>
	     	</div>
    	);
  	}
}

export default BasePage;

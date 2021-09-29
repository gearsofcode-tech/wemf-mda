import { Container } from "flux/utils";
import React, {Component} from "react";
import PaginationStore from "../data/PaginationStore";

class Pagination extends Component{


    constructor(props){
        super(props);
    }

    myState(){
        if (this.state[this.props.id]){
            return {
                currentPage: this.state[this.props.id].page,
                pageSize: this.state[this.props.id].pageSize,
                totalRecords: this.state[this.props.id].totalRecords
            };
        }
        return {
            currentPage: 0,
            pageSize: 0,
            totalRecords: 0
        }
    }

    
    
    start(){
        const paginationState = this.myState();
        return ((paginationState.currentPage -1) * paginationState.pageSize)+ 1;
    }

    
    
    end(){
        const paginationState = this.myState();
        let totalPages = Math.ceil(paginationState.totalRecords / paginationState.pageSize);
        if (paginationState.currentPage == totalPages) return paginationState.totalRecords;
        return this.start() + paginationState.pageSize - 1;
    }

    
    
    printLink(i){
        const paginationState = this.myState();
        return (
        <li key={i} className={i == paginationState.currentPage ? "paginate_button page-item active" : "paginate_button page-item"}>
                <a href="#" aria-controls="datatables-basic" data-dt-idx={i} tabIndex="0" className="page-link" onClick={event => this.props.navigate(i)}>{i}</a>
            </li>
        )
    }


    
    render(){
        const paginationState = this.myState();
        let totalPages = Math.ceil(paginationState.totalRecords / paginationState.pageSize);
        let links = [];
        for(var i=1; i<=totalPages; i++){
            links.push(this.printLink(i));
        }
        return (
            <div className="row dataTables_wrapper">
                <div className="col-md-5">
                    <div className="dataTables_info" id="datatables-basic_info" role="status" aria-live="polite">Showing from {this.start()} to {this.end()}, total of {paginationState.totalRecords} records</div>
                </div>
                <div className="col-md-7">
                    <div className="dataTables_paginate paging_simple_numbers">
                        <ul className="pagination">
                            <li className={paginationState.currentPage==1 ? "paginate_button page-item previous disabled":"paginate_button page-item previous"}>
                                <a href="#" aria-controls="datatables-basic" data-dt-idx="0" tabIndex="0" className="page-link" onClick={event => this.props.navigate(paginationState.currentPage-1)}>previous</a>
                            </li>
                            {links}
                            <li className={paginationState.currentPage == totalPages ? "paginate_button page-item next disabled" : "paginate_button page-item next"} id="datatables-basic_next">
                                <a href="#" aria-controls="datatables-basic" data-dt-idx="7" tabIndex="0" className="page-link" onClick={event => this.props.navigate(paginationState.currentPage+1)}>next</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        );
    }


    static getStores(){
        return [PaginationStore];
    }


    static calculateState(){
        return PaginationStore.getState();
    }
}

export default Container.create(Pagination);
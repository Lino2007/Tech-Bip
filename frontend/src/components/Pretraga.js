import React from 'react';
import '../css/pretraga.css';
import {SearchOutlined} from '@ant-design/icons';

const Pretraga = (props) => {
    return(
        <div className="pretraga">
            <div className="search">
            <input type="text" placeholder="Pretraga"></input>
            <SearchOutlined className="ikona"/>
            </div>
            <div className="pocisti"/>
        </div>
    );
}

export default Pretraga;
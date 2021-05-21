import React from 'react';
import '../css/zahtjev-forma.css';
import { DeleteOutlined } from '@ant-design/icons';
import { useHistory } from 'react-router';


const UredjivanjeZahtjeva = () => {
    const history = useHistory();

    return (<div className="prozor">
        <DeleteOutlined className="kantica" onClick={() => { history.goBack(); }} />
        <br />
        <div className="forma-grid">
            <div>
                <h2>Podaci o klijentu</h2>
                <input type="text" id="ime" disabled={true} value="ime"></input>
                <label for="ime">Ime</label>
                <input type="text" id="prezime" disabled={true} value="prezime"></input>
                <label for="prezime">Prezime</label>
                <input type="email" id="email" disabled={true} value="email@mail.com"></input>
                <label for="email">E-mail</label>
                <input type="text" id="telefon" disabled={true} value="000111222"></input>
                <label for="telefon" >Telefon</label>
                <input type="text" id="drzava" disabled={true} value="BiH"></input>
                <label for="drzava" >Drzava</label>
                <input type="text" id="grad" disabled={true} value="Sarajevo"></input>
                <label for="telefon" >Grad</label>
            </div>
            <div>
                <h2>Opis problema</h2>
                <input type="text" id="naslov" ></input>
                <label for="naslov">Naslov</label>
                <select id="kategorija">
                    <option>Hardver</option>
                    <option>Softver</option>
                    <option>Ostalo</option>
                </select>
                <label for="kategorija">Kategorija*</label>
                <textarea rows="8" id="opis"></textarea>
                <label for="opis">Detaljan opis*</label>
            </div>
            <div>
                <h2>Status zahtjeva</h2>
                <input type="radio" id="neobradjen" name="obrada" value="otkazan"></input>
                <label for="neobradjen" className="radio-label">Otkazan</label><br />
                <input type="radio" id="obradjen" name="obrada" value="obradjen"></input>
                <label for="obradjen" className="radio-label">Neobrađen</label><br />
                <input type="radio" id="neobradjen" name="obrada" value="neobradjen"></input>
                <label for="neobradjen" className="radio-label">Obrađen</label><br />
                <select className="agenti">
                    <option>Rjesenje1</option>
                    <option>Rjesenje2</option>
                </select>
                <button className="forma-btn"
                    onClick={() => { history.push("/unosrjesenja") }}>
                    Dodaj novo rješenje</button>
            </div>
        </div>
        <button className="spasi-btn">Spasi</button>
    </div>);
}

export default UredjivanjeZahtjeva;
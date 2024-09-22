import './Login.css'
import logo from '../../assets/swiftmanga_logo.png'
import { useState } from 'react';
import { Link } from 'react-router-dom';

export function Login () {
    const [selectedForm, setSelectedForm] = useState('login');

    const handlLoginChange = (event) => {
        setSelectedForm(event.target.value);
    };

    return (
        <div className='logincontainer'>
            <header className="header">
                <img src={logo} alt="Swift Manga logo" className='logoimg'/>
            </header>
            <div className="loginform">
                <div className="options">
                    <label className='option'>
                        <input type="radio" name='radio' value="login" checked ={selectedForm === 'login'} onChange={handlLoginChange}/>
                        <span className='name'>Login</span>
                    </label>
                    <label className='option'>
                        <input type="radio" name='radio' value="signup" checked={selectedForm === 'signup'} onChange={handlLoginChange}/>
                        <span className='name'>Cadastrar</span>
                    </label>
                </div>
                {selectedForm === 'login' ? (
                    <div className={`form-content ${selectedForm === 'login' ? 'active' : ''}`}>
                        <h2>Login</h2>
                        <form>
                            <div className="content">
                                <label>Usuário: </label>
                                <input type="text" placeholder='Nome de Usuário'/>
                            </div>
                            <div className="content">
                                <label>Senha: </label>
                                <input type="password" placeholder='Senha'/>
                            </div>
                            <button className='btn'><Link className='homelink' to="/home" type='submit'>Entrar</Link></button>
                        </form>
                    </div>
                ) : (
                    <div className={`form-content ${selectedForm === 'signup' ? 'active' : ''}`}>
                        <h2>Cadastrar</h2>
                        <form>
                            <div className="content">
                                <label>E-mail: </label>
                                <input type="email" placeholder='E-mail'/>
                            </div>
                            <div className="content">
                                <label>Usuário: </label>
                                <input type="text" placeholder='Usuário'/>
                            </div>
                            <div className="content">
                                <label>Senha: </label>
                                <input type="password" placeholder='Senha'/>
                            </div>
                            <div className='content'>
                                <label>Confirmar senha: </label>
                                <input type="password" placeholder='Confirme a senha'/>
                            </div>
                            <button className="btn">Cadastrar</button>
                        </form>
                    </div>
                )}
            </div>
        </div>
    );
}
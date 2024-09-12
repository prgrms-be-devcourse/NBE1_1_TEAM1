import '../Login.css';
import React, { useState } from "react";
import axios from 'axios';

export function Login({ onLoginSuccess }) {
    const [isSignUp, setIsSignUp] = useState(false);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSignUpClick = () => {
        setIsSignUp(true);
    };

    const handleSignInClick = () => {
        setIsSignUp(false);
    };

    const handleSignIn = async (e) => {
        e.preventDefault();
        try {
            // const response = await axios.post('http://localhost:8080/api/v1/login', { email, password });
            // const { token } = response.data;
            // localStorage.setItem('token', token);
            // axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
            onLoginSuccess();
        } catch (error) {
            console.error('Login failed:', error);
            alert('로그인에 실패했습니다. 다시 시도해주세요.');
        }
    };

    const handleSignUp = async (e) => {
        e.preventDefault();
        try {
            // 회원가입 동작
            setIsSignUp(false)
        } catch (error) {
            console.error('Login failed:', error);
            alert('로그인에 실패했습니다. 다시 시도해주세요.');
        }
    };

    return (
        <div className={`container ${isSignUp ? "right-panel-active" : ""}`} id="container">
            <div className="form-container sign-up-container">
                <form onSubmit={handleSignUp} className="login-form">
                    <h1>Create Account</h1>
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                    <input
                        type="password"
                        className="form-control"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                    <button>Sign Up</button>
                </form>
            </div>
            <div className="form-container sign-in-container">
                <form onSubmit={handleSignIn} className="login-form">
                    <h1>Sign in</h1>
                    <div className="form-floating">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="아이디 입력..."
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                        <label htmlFor="username">아이디</label>
                    </div>
                    <div className="form-floating">
                        <input
                            type="password"
                            className="form-control"
                            placeholder="비밀번호 입력..."
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                        <label htmlFor="password">비밀번호</label>
                    </div>
                    <button type="submit">Sign In</button>
                </form>
            </div>
            <div className="overlay-container">
                <div className="overlay">
                    <div className="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>  </p>
                        <button className="ghost" onClick={handleSignInClick}>Sign In</button>
                    </div>
                    <div className="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>  </p>
                        <button className="ghost" onClick={handleSignUpClick}>Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

import React from 'react';
import { Routes, Route, BrowserRouter as Router } from 'react-router-dom';
import HeaderComponent from '../../components/Header/Header';
import Main from '../main/Main';
import DownLoad from '../downLoad/downLoad';
import { GlobalStyle } from '../../helpers/globalStyle';
import { AppRoute } from '../../helpers/utils';
import styled from 'styled-components';
import Footer from '../../components/footer/footer';

const App = () => {
  return (
    <React.Fragment>
      <GlobalStyle />

      <Router basename="/">
        <HeaderComponent />
        <ContentPage>
          <Routes>
            <Route exact path={AppRoute.MAIN.url} element={<Main />} />
            <Route exact path={AppRoute.EXAMPLE.url} element={<DownLoad />} />
          </Routes>
        </ContentPage>
      </Router>
      <Footer />
    </React.Fragment>
  );
};

const ContentPage = styled.main`
  width: 100%;
  display: flex;
  aligin-items: center;
  padding-top: 50px;
  padding-bottom: 120px;
  justify-content: center;
`;

export default App;

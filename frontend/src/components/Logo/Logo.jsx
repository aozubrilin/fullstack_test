import React from 'react';
import { useLocation } from 'react-router';
import { Link } from 'react-router-dom';
import logo from '../../assets/icons/logo.svg';
import styled from 'styled-components';
import { AppRoute } from '../../helpers/utils';

const LogoComponent = () => {
  const { pathname } = useLocation();
  const isMainPage = AppRoute.MAIN.url === pathname;

  return isMainPage ? (
    <Logo>
      <LogoImage src={logo} alt="Логотип" width="150" height="150" />
    </Logo>
  ) : (
    <Logo>
      <Link to={AppRoute.MAIN.url}>
        <LogoImage src={logo} alt="Логотип" width="150" height="150" />
      </Link>
    </Logo>
  );
};

const Logo = styled.div`
  display: flex;
  flex-shrink: 0;
  justify-content: center;
  align-items: center;
`;

const LogoImage = styled.img`
  max-width: 100%;
  display: block;
  width: 150px;
  height: 150px;
`;

export default LogoComponent;

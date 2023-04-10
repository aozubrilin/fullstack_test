import React from 'react';
import styled from 'styled-components';
import LogoComponent from '../Logo/Logo';
import NavComponent from '../Nav/Nav';

const HeaderComponent = () => {
  return (
    <Header>
      <HeaderWrapper>
        <LogoComponent />
        <NavComponent />
      </HeaderWrapper>
    </Header>
  );
};

const Header = styled.header`
  display: flex;
  flex-shrink: 0;
  align-items: center;
  justify-content: center;
  width: 100%;
  z-index: 10;
  background-color: #000000;
`;

const HeaderWrapper = styled.div`
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;
  padding-top: 10px;
  padding-left: 30px;
  padding-right: 30px;
  padding-bottom: 22px;
  max-width: 1240px;
`;

export default HeaderComponent;

import React from 'react';
import { NavLink } from 'react-router-dom';
import styled from 'styled-components';
import { AppRoute } from '../../helpers/utils';

const NavComponent = ({ isMenuOpened, onLinkClick }) => {
  return (
    <Navigation>
      <NavigationList>
        {Object.keys(AppRoute).map((item, i) => (
          <NavigationItem key={AppRoute[item].title + i}>
            <StyledNavLink to={AppRoute[item].url} onClick={onLinkClick}>
              {AppRoute[item].title}
            </StyledNavLink>
          </NavigationItem>
        ))}
      </NavigationList>
    </Navigation>
  );
};

const Navigation = styled.nav`
  display: flex;
`;

const NavigationList = styled.ul`
  display: flex;
  margin: 0;
  padding-top: 15px;
  padding-left: 0;
  list-style: none;
`;

const NavigationItem = styled.li`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;

  &:first-child {
    margin-right: 27px;
  }
`;

const StyledNavLink = styled(NavLink)`
  position: relative;
  color: #ffffff;
  font-size: 20px;
  line-height: 23px;
  cursor: pointer;
  transform: color 0.3s ease;
  text-decoration: none;

  &.active::after {
    content: '';
    position: absolute;
    left: 0;
    bottom: -4px;
    width: 100%;
    height: 1px;
    background-color: #f44a1e;
  }

  @media (hover: hover), screen and (min-width: 0\0) {
    &:hover {
      color: #f44a1e;
    }
  }
`;

export default NavComponent;

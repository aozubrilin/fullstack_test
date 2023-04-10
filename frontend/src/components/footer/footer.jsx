import React, { useState, useEffect } from 'react';
import styled from 'styled-components';

const Footer = () => {
  const [currentYear, setCurrentYear] = useState(null);

  useEffect(() => {
    setCurrentYear(new Date().getFullYear());
  }, []);

  return (
    <PageFooter>
      <FooterWrapper>
        <FooterCopyright>
          <span>Copyright © {currentYear}</span>
        </FooterCopyright>
      </FooterWrapper>
    </PageFooter>
  );
};

const PageFooter = styled.footer`
  display: flex;
  background-color: #000000;
  justify-content: center;
  width: 100%;
`;

const FooterWrapper = styled.div`
  display: flex;
  justify-content: flex-start;
  padding-top: 37px;
  padding-bottom: 41px;

  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;

  padding-left: 30px;
  padding-right: 30px;

  max-width: 1240px;
`;

const FooterCopyright = styled.div`
  span {
    font-size: 20px;
    line-height: 23px;
  }
`;
export default Footer;

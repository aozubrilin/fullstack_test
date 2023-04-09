import React, { useState } from 'react';
import styled from 'styled-components';

const Main = styled('div')`
  height: 100px;
  border: none;
  z-index: 10;
`;

const DropDownContainer = styled('div')`
  width: 10.5em;
  margin: 0 auto;
`;

const DropDownHeader = styled('div')`
  margin-bottom: 0.8em;
  padding: 0.4em 2em 0.4em 1em;
  border: 2px solid #f44a1e;
  font-weight: 500;
  font-size: 1.3rem;
  color: #f44a1e;
  background: #ffffff;
  cursor: pointer;
`;

const DropDownListContainer = styled('div')``;

const DropDownList = styled('ul')`
  padding: 0;
  margin: 0;
  padding-left: 1em;
  background: #ffffff;
  border: 2px solid #f44a1e;
  box-sizing: border-box;
  color: #f44a1e;
  font-size: 1.3rem;
  font-weight: 500;
  cursor: pointer;
  &:first-child {
    padding-top: 0.8em;
  }
`;

const ListItem = styled('li')`
  list-style: none;
  margin-bottom: 0.8em;
`;

const options = ['All', 'Animals', 'Fantastic', 'Music'];

const Select = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedOption, setSelectedOption] = useState(null);

  const toggling = () => setIsOpen(!isOpen);

  const onOptionClicked = (value) => () => {
    setSelectedOption(value);
    setIsOpen(false);
    console.log(selectedOption);
  };

  return (
    <Main>
      <DropDownContainer>
        <DropDownHeader onClick={toggling}>
          {selectedOption || 'All'}
        </DropDownHeader>
        {isOpen && (
          <DropDownListContainer>
            <DropDownList>
              {options.map((option) => (
                <ListItem onClick={onOptionClicked(option)} key={Math.random()}>
                  {option}
                </ListItem>
              ))}
            </DropDownList>
          </DropDownListContainer>
        )}
      </DropDownContainer>
    </Main>
  );
};

export default Select;

import React from 'react';
import Select from '../../components/Select/Select';
import styled from 'styled-components';

const data = [
  {
    id: 1,
    filename: '191.jpg',
    category: 'Животные',
    url: 'https://www.meme-arsenal.com/memes/4c794fd85660218d1b0f768a594eeac7.jpg',
  },
  {
    id: 2,
    filename: '191.jpg',
    category: 'Животные',
    url: 'https://www.meme-arsenal.com/memes/4c794fd85660218d1b0f768a594eeac7.jpg',
  },
  {
    id: 3,
    filename: '191.jpg',
    category: 'Животные',
    url: 'https://www.meme-arsenal.com/memes/4c794fd85660218d1b0f768a594eeac7.jpg',
  },
  {
    id: 4,
    filename: '191.jpg',
    category: 'Животные',
    url: 'https://www.meme-arsenal.com/memes/4c794fd85660218d1b0f768a594eeac7.jpg',
  },
  {
    id: 5,
    filename: '191.jpg',
    category: 'Животные',
    url: 'https://www.meme-arsenal.com/memes/4c794fd85660218d1b0f768a594eeac7.jpg',
  },
  {
    id: 6,
    filename: '191.jpg',
    category: 'Животные',
    url: 'https://www.meme-arsenal.com/memes/4c794fd85660218d1b0f768a594eeac7.jpg',
  },
  {
    id: 7,
    filename: '191.jpg',
    category: 'Животные',
    url: 'https://www.meme-arsenal.com/memes/4c794fd85660218d1b0f768a594eeac7.jpg',
  },
  {
    id: 8,
    filename: '191.jpg',
    category: 'Животные',
    url: 'https://www.meme-arsenal.com/memes/4c794fd85660218d1b0f768a594eeac7.jpg',
  },
];

const Main = () => {
  return (
    <MainWrapper>
      <Select />
      <ImagesList>
        {data.map((item) => (
          <ImageItem key={item.id}>
            <Image src={item.url} alt={item.name} />
          </ImageItem>
        ))}
      </ImagesList>
    </MainWrapper>
  );
};

const MainWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  max-width: 1240px;
`;

const ImagesList = styled.ul`
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  justify-content: space-between;
`;

const ImageItem = styled.li`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 45px;
  width: 325px;
  height: 325px;
  border: 1px solid #f44a1e;
`;

const Image = styled.img`
  width: 100%;
  height: 100%;
  filter: drop-shadow(0 0 58px rgba(244, 74, 30, 0.25));
`;

export default Main;

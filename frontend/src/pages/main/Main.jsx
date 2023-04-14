import React, { useState, useEffect } from 'react';
import Select from '../../components/Select/Select';
import styled from 'styled-components';
import { getCatrgoryImages } from '../../services/getImage';

const Main = () => {
  const [images, setImages] = useState(null);
  const [selectedOption, setSelectedOption] = useState('all');

  const loadImages = async (selectedOption) => {
    let images = await getCatrgoryImages(selectedOption);
    setImages(images);
    console.log('Main images', images);
  };

  useEffect(() => {
    loadImages(selectedOption);
  }, [selectedOption]);

  return (
    <MainWrapper>
      <Select
        setSelectedOption={setSelectedOption}
        selectedOption={selectedOption}
      />
      <ImagesList>
        {images != null &&
          images.map((item) => (
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
  width: 350px;
  height: 350px;
  border: 1px solid #f44a1e;
`;

const Image = styled.img`
  width: 100%;
  height: 100%;
  object-fit: cover;
  filter: drop-shadow(0 0 58px rgba(244, 74, 30, 0.25));
`;

export default Main;

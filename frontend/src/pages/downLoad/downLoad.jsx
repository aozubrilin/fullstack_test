import React, { useState } from 'react';
import styled from 'styled-components';
import { PostImages } from '../../services/getImage';

// https://www.meme-arsenal.com/memes/9e383a2c7a04ba1db7b4b09c36f6b372.jpg

const DownLoad = () => {
  const [formData, setFormData] = useState({
    file: null,
    filename: '',
    category: 'all',
    link: '',
  });

  const [nameInputFile, setNameInputFile] = useState('');

  const onSubmit = (e) => {
    e.preventDefault();
    const form = new FormData();
    console.log('Upload formData', formData);
    form.append('file', formData.file);
    form.append('filename', formData.filename);
    form.append('category', formData.category);
    form.append('link', formData.link);
    PostImages(form);
    setFormData({
      ...formData,
      file: null,
      filename: '',
      category: 'all',
      link: '',
    });
    setNameInputFile('');
  };

  return (
    <Form onSubmit={onSubmit}>
      <FormGroup>
        <label htmlFor="file">Фаил:</label>
        <input
          value={nameInputFile}
          type="file"
          name="file"
          onChange={(e) => {
            setFormData({
              ...formData,
              file: e.target.files[0],
              link: '',
            });
            setNameInputFile(e.target.value);
          }}
        />
        <label htmlFor="link">Ссылка:</label>
        <input
          value={formData.link}
          type="text"
          name="link"
          onChange={(e) => {
            setFormData({ ...formData, link: e.target.value, file: null });
            setNameInputFile('');
          }}
        />
        <label htmlFor="filename">Название фаила:</label>
        <input
          required
          value={formData.filename}
          type="text"
          name="filename"
          onChange={(e) =>
            setFormData({ ...formData, filename: e.target.value })
          }
        />
        <label htmlFor="categoty">Категория:</label>
        <select
          name="category"
          value={formData.category}
          onChange={(e) =>
            setFormData({ ...formData, category: e.target.value })
          }
        >
          <option value="all">all</option>
          <option value="nature">nature</option>
          <option value="animal">animal</option>

          <option value="fantasy">fantasy</option>
        </select>
      </FormGroup>

      <button className="btn btn-primary" type="submit">
        Upload
      </button>
    </Form>
  );
};

const Form = styled('form')`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 500px;

  input[type='text'] {
    // background: #00aec9;
    color: #f44a1e;
    cursor: pointer;
    margin-bottom: 0;
    width: 100%;
    border-radius: 5px;
    height: 35px;
    border-color: transparent;
    box-shadow: 0px;
    outline: none;
    transition: 0.15s;
    font-size: 18px;
    text-align: center;
  }

  input[type='file'],
  select {
    color: #f44a1e;
    cursor: pointer;
    margin-bottom: 0;
    width: 100%;
    font-size: 18px;
    border-radius: 5px;
    height: 35px;
    border-color: transparent;
    box-shadow: 0px;
    outline: none;
    transition: 0.15s;
    text-align: center;
  }

  button {
    max-width: 100px;
    background: #00aec9;
    cursor: pointer;
    margin-bottom: 0;
    width: 100%;
    border-radius: 5px;
    height: 35px;
    border-color: transparent;
    box-shadow: 0px;
    outline: none;
    transition: 0.15s;
    font-size: 18px;
    text-align: center;
    margin-top: 20px;
  }
`;

const FormGroup = styled('div')`
  display: flex;
  flex-direction: column;

  gap: 10px;
`;

export default DownLoad;

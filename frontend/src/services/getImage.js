import axios from 'axios';

const BACKEND_URL = `https://fullstacktest-production.up.railway.app`;
const REQUEST_TIMEOUT = 5000;

const api = axios.create({
  baseURL: BACKEND_URL,
  timeout: REQUEST_TIMEOUT,
});

export const getCatrgoryImages = async (category) => {
  const res = await api.get(`/?category=${category}`).catch((err) => {
    console.log('GET ERROR', err);
    throw err;
  });

  return await res.data.fileList;
};

const URL_POST = 'https://7b73-91-201-74-74.ngrok-free.app/upload';

const config = {
  headers: { 'content-type': 'multipart/form-data' },
};

export const PostImages = async (formData) => {
  axios
    .post(URL_POST, formData, config)
    .then((response) => {
      console.log('POST response', response);
    })
    .catch((error) => {
      console.log('POST ERROR', error);
    });
};

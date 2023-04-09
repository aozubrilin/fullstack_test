import axios from 'axios';

const BASE_URL = 'https://api.thecatapi.com/v1/images/search';

export const getImage = async () => {
  axios.defaults.headers.common['x-api-key'] = process.env.REACT_APP_API_KEY;
  const res = await axios
    .get(`${BASE_URL}`, { params: { limit: 1, size: 'full' } })
    .catch((err) => {
      throw err;
    });

  return await res.data[0].url;
};

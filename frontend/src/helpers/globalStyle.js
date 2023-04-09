import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

export const GlobalStyle = createGlobalStyle`
  ${reset}
   *,
    *::before,
    *::after {
    box-sizing: border-box;
    }

    html {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    }

    body {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;

    font-style: normal;
    font-weight: 400;
    font-size: 24px;
    line-height: 34px;
    font-family: "Roboto", "Arial", sans-serif;
    color:  #ffffff;

    background-color:  #111111;

    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;

    // оптимизация выравнивания шрифта относительно центра строки
    text-rendering: optimizeLegibility;
    }

    a {
    color: #ffffff;
    text-decoration: none;
    }

    img,
    video {
    display: block;
    max-width: 100%;
    height: auto;
    }

    button,
    [type="button"],
    [type="reset"],
    [type="submit"] {
    appearance: none;
    }

    .wrapper {
  position: relative;

  display: flex;
  align-items: center;
  flex-direction: column;
  min-height: 100vh;
  min-height: calc(100 * var(--vh, 1vh));
  overflow: hidden;

  &--no-scrollbar {
    height: 100vh;
    height: calc(100 * var(--vh, 1vh));

    -ms-overflow-style: none;
    scrollbar-width: none;

    &::-webkit-scrollbar {
      display: none;
    }
  }

  .header,
  .footer {
    flex-shrink: 0;
  }

  main {
    flex-grow: 1;
  }
}

.visually-hidden {
  position: absolute;

  width: 1px;
  height: 1px;
  margin: -1px;

  clip: rect(0 0 0 0);
}

.no-transition {
  transition: none !important;
}
`;

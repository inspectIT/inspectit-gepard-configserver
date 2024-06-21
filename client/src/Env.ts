const { VITE_API_BASE_URL, ...otherViteConfig } = import.meta.env;

//  Adds Type-Safety to the ENV-Variables
export const Env = {
    API_BASE_URL: VITE_API_BASE_URL as string,
    __vite__: otherViteConfig,
};

// To make it easy to figure out the frontend environment config at any moment
console.log(Env);


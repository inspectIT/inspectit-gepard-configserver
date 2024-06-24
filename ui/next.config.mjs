/** @type {import('next').NextConfig} */

const isProduction = process.env.NODE_ENV === 'production';

const nextConfig = {
    output: 'export',
    distDir: 'build',
    trailingSlash: true,
    // assetPrefix: isProduction ? '/ui' : '/',


};

export default nextConfig;

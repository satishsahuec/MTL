module.exports = function generateRandomNumber() {
  return Math.random().toString() +
    Math.random().toString() +
    Math.random().toString();
};
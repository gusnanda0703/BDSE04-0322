const hamburgerMenu = document.querySelector(".hamburger-menu");

hamburgerMenu.addEventListener("click", () => {
  const navLinks = document.querySelector(".nav-links");
  const navButton = document.querySelector(".nav-button");
  const menu2 = document.querySelector(".hamburger-menu span:nth-child(2)");
  const menu3 = document.querySelector(".hamburger-menu span:nth-child(3)");

  navLinks.classList.toggle("active");
  navButton.classList.toggle("active");
  menu2.classList.toggle("active");
  menu3.classList.toggle("active");
});

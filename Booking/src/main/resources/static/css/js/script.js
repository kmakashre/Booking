//nav bar
// Hamburger toggle
document.querySelector('.hamburger').addEventListener('click', () => {
    document.querySelector('.nav-links').classList.toggle('active');
});

// Dropdown toggle (for mobile)
document.querySelectorAll('.dropdown-toggle').forEach(item => {
    item.addEventListener('click', (e) => {
        e.preventDefault();
        const parent = item.parentElement;
        parent.classList.toggle('active');
    });
});

// Search box toggle
document.querySelector('.search-icon').addEventListener('click', () => {
    document.querySelector('.search-box').classList.toggle('active');
});

// Close search
document.querySelector('#closeSearch').addEventListener('click', () => {
    document.querySelector('.search-box').classList.remove('active');
});
// Hamburger Menu Toggle
document.getElementById('hamburger').addEventListener('click', function () {
    const navLinks = document.getElementById('navLinks');
    navLinks.classList.toggle('active');
});

// Dropdown Toggle for Mobile
document.querySelectorAll('.dropdown-toggle').forEach(function (dropdownToggle) {
    dropdownToggle.addEventListener('click', function (e) {
        if (window.innerWidth <= 768) {
            e.preventDefault(); // Prevent default link behavior on mobile
            const dropdown = this.parentElement;
            dropdown.classList.toggle('active');
        }
    });
});

// Search Box Toggle
document.getElementById('searchIcon').addEventListener('click', function () {
    const searchBox = document.getElementById('searchBox');
    searchBox.style.display = searchBox.style.display === 'block' ? 'none' : 'block';
});

document.getElementById('closeSearch').addEventListener('click', function () {
    document.getElementById('searchBox').style.display = 'none';
});
  //Social-media icon

  function toggleIcons() {
      var icons = document.querySelector(".hidden-icons");
      if (icons.style.display === "flex") {
          icons.style.display = "none";
      } else {
          icons.style.display = "flex";
      }
  }

//***************************  for whatsapp****************************
function openWhatsApp() {
    var phoneNumber = "917987008427"; // Replace with your number
    var message = "Hello, I want to chat with you";
    var url = "https://web.whatsapp.com/" + phoneNumber + "?text=" + encodeURIComponent(message);
    window.open(url, "_blank");
}

// for calling
function makeCall() {
        window.location.href = "tel:+917987008427"; // Replace with your number
    }

//for socile
 function toggleIcons() {
        const plusIcon = document.querySelector('.plus-icon');
        const socialIcons = document.querySelector('.floating-social-icons');
        plusIcon.classList.toggle('rotated');
        socialIcons.classList.toggle('show');
    }

    function initiateCall() {
        fetch('/initiate-call', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        })
        .then(response => response.text())
        .then(data => alert(data))
        .catch(error => alert('Error: ' + error));
    }
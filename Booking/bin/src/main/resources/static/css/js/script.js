

document.querySelector("form").addEventListener("submit", async function (event) {
    event.preventDefault();

    const name = document.getElementById("name").value;
    const mobile = document.getElementById("mobile").value;

    const userData = { name, mobile };

    const response = await fetch("http://localhost:8080/api/users/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(userData)
    });

    if (response.ok) {
        window.location.href = "address.html"; // Redirect to Address Page
    } else {
        alert("Error: Could not save data!");
    }
});

// /********************//Search-icon*********************/
document.addEventListener("DOMContentLoaded", function () {
    const searchIcon = document.getElementById("searchIcon");
    const searchBox = document.getElementById("searchBox");
    const closeSearch = document.getElementById("closeSearch");

    if (searchIcon && searchBox && closeSearch) {
        searchIcon.addEventListener("click", function (event) {
            event.stopPropagation(); // Prevents click from closing immediately
            searchBox.style.display = "block"; // Show search bar
        });

        closeSearch.addEventListener("click", function (event) {
            event.stopPropagation();
            searchBox.style.display = "none"; // Hide search bar
        });

        // Hide search when clicking outside
        document.addEventListener("click", function (event) {
            if (!searchBox.contains(event.target) && !searchIcon.contains(event.target)) {
                searchBox.style.display = "none";
            }
        });
    } else {
        console.error("Search elements not found! Check your HTML IDs.");
    }
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


//   *************************booking*********************//
document.getElementById('mobileForm').addEventListener('submit', function(e) {
    e.preventDefault();

    // Show loading state
    const btn = document.querySelector('#mobileForm .proceed-btn');
    btn.disabled = true;
    btn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Processing...';

    // Collect form data
    const formData = new FormData(this);

    // Submit form via AJAX
    fetch(this.action, {
        method: 'POST',
        body: formData,
        headers: {
            'Accept': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) throw new Error('Network response was not ok');
        return response.json();
    })
    .then(data => {
        if (data.success) {
            // Load the next step (address.html) content
            fetch('address.html')
                .then(response => response.text())
                .then(html => {
                    // Create a temporary container to parse the HTML
                    const tempDiv = document.createElement('div');
                    tempDiv.innerHTML = html;

                    // Extract just the booking section from the loaded HTML
                    const newBookingContent = tempDiv.querySelector('#booking').innerHTML;

                    // Replace only the booking section content
                    document.getElementById('booking').innerHTML = newBookingContent;

                    // Update the active step in the progress indicator
                    updateActiveStep('address');
                });
        } else {
            alert(data.message || 'Error occurred');
            btn.disabled = false;
            btn.innerHTML = 'Proceed';
        }
    })
    .catch(error => {
        console.error('Error:', error);
        btn.disabled = false;
        btn.innerHTML = 'Proceed';
    });
});

// Function to update the active step in the progress indicator
function updateActiveStep(step) {
    const steps = document.querySelectorAll('.steps span');
    steps.forEach(span => span.classList.remove('active'));

    switch(step) {
        case 'mobile':
            steps[0].classList.add('active');
            break;
        case 'address':
            steps[1].classList.add('active');
            break;
        case 'schedule':
            steps[2].classList.add('active');
            break;
        case 'sell':
            steps[3].classList.add('active');
            break;
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
function fetchItems() {
    fetch('/patient/get')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('#patient-demographics tbody');
            tableBody.innerHTML = ''; // Clear existing data if any
            console.log(data);
            // Loop through the data and create table rows
            data.forEach(item => {
                const row = document.createElement('tr');

                //Name column
                const nameCell = document.createElement("td");
                nameCell.innerText = item.name;
                nameCell.contentEditable = true;
                row.appendChild(nameCell);

                //Family Name column
                const familyNameCell = document.createElement("td");
                familyNameCell.innerText = item.familyName;
                familyNameCell.contentEditable = true;
                row.appendChild(familyNameCell);

                //DOB column
                const dobCell = document.createElement("td");
                dobCell.innerText = item.dateOfBirth;
                dobCell.contentEditable = true;
                row.appendChild(dobCell);

                //Gender column
                const genderCell = document.createElement("td");
                genderCell.innerText = item.sex;
                genderCell.contentEditable = true;
                row.appendChild(genderCell);

                //Home address column
                const homeAddressCell = document.createElement("td");
                homeAddressCell.innerText = item.homeAddress;
                homeAddressCell.contentEditable = true;
                row.appendChild(homeAddressCell);

                //Phone Num column
                const phoneNumberCell = document.createElement("td");
                phoneNumberCell.innerText = item.phoneNumberCell;
                phoneNumberCell.contentEditable = true;
                row.appendChild(phoneNumberCell);

                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching data:', error));
}



// Function to update the table when the button is clicked
function updateTable() {
    fetchItems();
}

// Function to handle form submission and add new row to the table
function addNewPatient(event) {
    event.preventDefault();
    const form = event.target;
    const patientName = form.name.value;
    const familyName = form.familyName.value;
    const dob = form.dob.value;
    const gender = form.gender.value;
    const address = form.address.value;
    const phone = form.phone.value;


    // Create a new row for the new item and add it to the table
    const tableBody = document.querySelector('#patient-demographics tbody');
    const newRow = document.createElement('tr');
    newRow.innerHTML = `
        <td contenteditable="true">${patientName}</td>
        <td contenteditable="true">${familyName}</td>
        <td contenteditable="true">${dob}</td>
        <td contenteditable="true">${gender}</td>
        <td contenteditable="true">${address}</td>
        <td contenteditable="true">${phone}</td>
    `;
    tableBody.appendChild(newRow);

    // Clear the form fields
    form.reset();

    const formData = {
        name: patientName,
        familyName: familyName,
        dateOfBirth: dob,
        sex: gender,
        homeAddress: address,
        phoneNumber: phone
    };

    // Send the form data to the POST API using fetch
    fetch('/patient/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData),
    })
    .then(response => response.json())
    .then(data => {
        // Handle the API response, if needed
        console.log('API response:', data);
        location.assign("./");
    })
    .catch(error => console.error('Error submitting data:', error));
}

// Call the fetchItems function when the page loads
window.onload = fetchItems;

// Add a click event listener to the "Update Table" button
document.getElementById('updateButton').addEventListener('click', updateTable);

// Add a submit event listener to the form
document.getElementById('addPatientForm').addEventListener('submit', addNewPatient);


function saveChanges() {
    const table = document.getElementById("patient-demographics");
    const rows = table.getElementsByTagName("tr");

    const updatedPatients = [];

    for (let i = 1; i < rows.length; i++) {
        const row = rows[i];
        const cells = row.getElementsByTagName("td");

        const name = cells[0].innerText.trim();
        const familyName = cells[1].innerText.trim();
        const dob = cells[2].innerText.trim();
        const gender = cells[3].innerText.trim();
        const homeAddress = cells[4].innerText.trim();
        const phoneNumber = cells[5].innerText.trim();

        // Push the updated data into an array
        updatedPatients.push({ name, familyName, dob, gender, homeAddress, phoneNumber });
    }

    updateBackendData(updatedPatients);

}

function updateBackendData(updatedPatients) {
    // Make a PUT request to the server using AJAX or fetch API
    fetch('/patient/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedPatients),
    })
    .then(response => response.json())
    .then(data => {
        // Handle the response from the server, if needed
        console.log('Data updated successfully:', data);

        // If you want to update the table with any changes returned from the server
        // For example, you could update the table with the server-generated IDs.
        // ...
    })
    .catch(error => {
        console.error('Error updating data:', error);
    });
}

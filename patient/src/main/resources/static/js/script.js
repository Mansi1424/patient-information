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
                        row.innerHTML = `
                            <td>${item.name}</td>
                            <td>${item.familyName}</td>
                            <td>${item.dateOfBirth}</td>
                            <td>${item.sex}</td>
                            <td>${item.homeAddress}</td>
                            <td>${item.phoneNumber}</td>
                        `;
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
                <td>${patientName}</td>
                <td>${familyName}</td>
                <td>${dob}</td>
                <td>${gender}</td>
                <td>${address}</td>
                <td>${phone}</td>
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
            })
            .catch(error => console.error('Error submitting data:', error));
        }

        // Call the fetchItems function when the page loads
        window.onload = fetchItems;


        // Add a click event listener to the "Update Table" button
        document.getElementById('updateButton').addEventListener('click', updateTable);

        // Add a submit event listener to the form
        document.getElementById('addPatientForm').addEventListener('submit', addNewPatient);

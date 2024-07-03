async function submitForm() {
    const form = document.getElementById('equipmentForm');
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    // Валидация полей
    const requiredFields = ['category', 'product_line', 'name_product', 'serial_number', 'status', 'location', 'name'];
    for (const field of requiredFields) {
        if (!data[field]) {
            alert(`Please fill in the ${field} field.`);
            return;
        }
    }

    try {
        const response = await fetch('/api/v1/equipment', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert('Equipment added successfully!');
            form.reset();
        } else {
            const errorData = await response.json();
            alert('Error adding equipment: ' + errorData.message);
        }
    } catch (error) {
        alert('Error adding equipment: ' + error.message);
    }
}
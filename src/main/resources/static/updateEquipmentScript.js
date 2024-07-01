document.addEventListener('DOMContentLoaded', async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');

    if (id) {
        try {
            const response = await fetch(`/api/v1/equipment/${id}`);
            const equipment = await response.json();

            document.getElementById('id_equipment').value = equipment.id_equipment;
            document.getElementById('category').value = equipment.category;
            document.getElementById('product_line').value = equipment.product_line;
            document.getElementById('name_product').value = equipment.name_product;
            document.getElementById('serial_number').value = equipment.serial_number;
            document.getElementById('description').value = equipment.description;
            document.getElementById('market_price').value = equipment.market_price;
            document.getElementById('purchase_price').value = equipment.purchase_price;
            document.getElementById('comment').value = equipment.comment;
            document.getElementById('status').value = equipment.status;
            document.getElementById('location').value = equipment.location;
            document.getElementById('name').value = equipment.name;
        } catch (error) {
            console.error('Error fetching equipment:', error);
        }
    }
});

async function submitUpdateForm() {
    const id_equipment = document.getElementById('id_equipment').value;
    const category = document.getElementById('category').value;
    const product_line = document.getElementById('product_line').value;
    const name_product = document.getElementById('name_product').value;
    const serial_number = document.getElementById('serial_number').value;
    const description = document.getElementById('description').value;
    const market_price = document.getElementById('market_price').value;
    const purchase_price = document.getElementById('purchase_price').value;
    const comment = document.getElementById('comment').value;
    const status = document.getElementById('status').value;
    const location = document.getElementById('location').value;
    const name = document.getElementById('name').value;

    const data = {
        category,
        product_line,
        name_product,
        serial_number,
        description,
        market_price,
        purchase_price,
        comment,
        status,
        location,
        name
    };

    try {
        const response = await fetch(`/api/v1/equipment/${id_equipment}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert('Equipment updated successfully!');
            window.location.href = '/';
        } else {
            const errorData = await response.json();
            alert('Error updating equipment: ' + errorData.message);
        }
    } catch (error) {
        alert('Error updating equipment: ' + error.message);
    }
}
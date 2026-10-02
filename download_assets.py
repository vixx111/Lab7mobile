import urllib.request
import json
import os

drawable_dir = "app/src/main/res/drawable"
os.makedirs(drawable_dir, exist_ok=True)

# Image URLs for high quality assets matching Ulysse Nardin Marine Torpilleur & Patrick Pruniaux
images = {
    "ic_watch_torpilleur.png": "https://www.ulysse-nardin.com/media/catalog/product/1/1/1183-310-7m_42.png",
    "ic_movement_un118.png": "https://watchesbysjx.com/wp-content/uploads/2017/09/Ulysse-Nardin-Marine-Torpilleur-Blue-5.jpg",
    "ic_case_side.png": "https://watchesbysjx.com/wp-content/uploads/2017/09/Ulysse-Nardin-Marine-Torpilleur-Blue-6.jpg",
    "ic_clasp.png": "https://watchesbysjx.com/wp-content/uploads/2017/09/Ulysse-Nardin-Marine-Torpilleur-Blue-7.jpg",
    "ic_patrick_pruniaux.jpg": "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/51299971939109.5be2d7c50352d.jpg"
}

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36'
}

for filename, url in images.items():
    dest_path = os.path.join(drawable_dir, filename)
    print(f"Downloading {filename} from {url}...")
    try:
        req = urllib.request.Request(url, headers=headers)
        with urllib.request.urlopen(req, timeout=10) as response, open(dest_path, 'wb') as out_file:
            out_file.write(response.read())
        print(f"Saved {filename} ({os.path.getsize(dest_path)} bytes)")
    except Exception as e:
        print(f"Failed to download {filename}: {e}")

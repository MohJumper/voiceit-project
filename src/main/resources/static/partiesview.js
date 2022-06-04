function runLoad(isAdmin) {
  debugger
  const actionHeader = document.getElementById('action')
  actionHeader.style.display = 'none'

  const elements = document.getElementsByClassName("actionButtons");

  elements.map(e => {
    e.style.display = 'none'
    return e
  })
  
  }
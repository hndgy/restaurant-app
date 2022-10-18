import React from 'react'

function Popup() {
  return (
    <div className="modal">
        <div className="modal-dialog" role="document">
            <div className="modal-content">
            <div className="modal-header">
                <h5 className="modal-title">Modal title</h5>
                <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close">
                <span aria-hidden="true"></span>
                </button>
            </div>
            <div className="modal-body">
                <p>test</p>
            </div>
            <div className="modal-footer">
                <button type="button" className="btn btn-primary">Save changes</button>
                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
            </div>
        </div>
</div>
  )
}

export default Popup